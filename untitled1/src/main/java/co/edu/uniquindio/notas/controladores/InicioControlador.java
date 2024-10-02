package co.edu.uniquindio.notas.controladores;
import co.edu.uniquindio.notas.modelo.Contacto;
import co.edu.uniquindio.notas.modelo.ContactoPrincipal;
import com.sun.javafx.charts.Legend;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class InicioControlador implements Initializable {
    public Button btnNuevoContacto;
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNumero;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private TextField txtUrlFoto;

    @FXML
    private ImageView imgFotoPerfil;

    @FXML
    private TableColumn<Contacto, String> colUrlFoto;  // Columna para la URL


    @FXML
    private Label lblNombre;
    @FXML
    private Label lblNumero;












    private final ContactoPrincipal contactoPrincipal;

    @FXML
    private TableView<Contacto> tablaNotas;


    @FXML
    private TableColumn<Contacto, String> colNombre;

    @FXML
    private TableColumn<Contacto, String> colApellido;

    @FXML
    private TableColumn<Contacto, String> colCorreo;

    @FXML
    private TableColumn<Contacto, String> colNumero;

    @FXML
    private TableColumn<Contacto, String> colNacimiento;









    @FXML
    private TableColumn<Contacto, String> colFecha;
    @FXML
    private TableColumn<Contacto, Integer> colId;
    private Legend.LegendItem txtId;


    public InicioControlador() {
        contactoPrincipal = new ContactoPrincipal();
    }

    @FXML
    public void crearContacto(ActionEvent e) {
        try {
            // Recoger los datos de los campos de texto y el DatePicker
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String numero = txtNumero.getText();
            LocalDate fechaNacimiento = dateNacimiento.getValue();
            String urlFoto = txtUrlFoto.getText();

            // Comprobación de campos vacíos
            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || numero.isEmpty() || fechaNacimiento == null) {
                throw new Exception("Todos los campos deben estar llenos.");
            }

            // Comprobación de que el número de teléfono sea válido (solo dígitos)
            if (!numero.matches("\\d+")) {
                throw new Exception("El número de teléfono debe contener solo dígitos.");
            }

            // Obtener la fecha de creación (actual)
            LocalDate fechaCreacion = LocalDate.now();

            // Usar el método en ContactoPrincipal para agregar un contacto
            contactoPrincipal.agregarContacto(nombre, apellido, correo, numero, fechaNacimiento, fechaCreacion, urlFoto);
            Image imagenPerfil = new Image(urlFoto);
            imgFotoPerfil.setImage(imagenPerfil);
            // Mostrar alerta de éxito
            mostrarAlerta("Contacto creado correctamente", Alert.AlertType.INFORMATION);

            // Limpiar los campos de texto y el DatePicker
            txtNombre.clear();
            txtApellido.clear();
            txtCorreo.clear();
            txtNumero.clear();
            dateNacimiento.setValue(null);
            txtUrlFoto.clear();

            // Actualizar la tabla
            actualizarTabla();

        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){


        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        colNumero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumero()));
        colNacimiento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaNacimiento().toString()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaCreacion().toString()));

        actualizarTabla();
        // Agregar datos a la tabla



    }
    private void actualizarTabla() {

        tablaNotas.setItems(FXCollections.observableArrayList(contactoPrincipal.listarNotas()));
    }









}
