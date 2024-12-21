/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapp;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
//import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane main_form;
    
   
    @FXML
    private Button btncancel;
    
      public  void  btncancel(){
        System.exit(0);
    }

    @FXML
    private TextField emp_adress;
    @FXML
    private Button backbtn;
    @FXML
    private Label homepresent;
    @FXML
    private Label username;
    @FXML
    private Button homebtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button salarybtn;
    @FXML
    private Button outupbtn;
    @FXML
    private AnchorPane home_form;
    @FXML
    private Label homr_totalemp;
    @FXML
    private Label home_totalpresents;
    @FXML
    private Label home_totalactiveemp;
    @FXML
    private BarChart<?, ?> home_chart;
    @FXML
    private AnchorPane employee_form;
    @FXML
    private TableView<EmpData> table_salary;
    @FXML
    private TableView<EmpData> emp_tableview;
    @FXML
    private TableColumn<EmpData, String> emp_colid;
    @FXML
    private TableColumn<EmpData, String> emp_colfirstname;
    @FXML
    private TableColumn<EmpData, String> emp_colastname;
    @FXML
    private TableColumn<EmpData, String> emp_colgender;
    @FXML
    private TableColumn<EmpData, String> emp_colphone;
    @FXML
    private TableColumn<EmpData, String> emp_coladdress;
    @FXML
    private TableColumn<EmpData, String> emp_col_position;
    @FXML
    private TableColumn<EmpData, String> emp_coldatsmeber;
    @FXML
    private TextField emp_search;
    @FXML
    private TextField emp_id;
    @FXML
    private TextField emp_firstname;
    @FXML
    private TextField emp_lastname;
    @FXML
    private ComboBox<?> emp_gender;
    @FXML
    private TextField emp_phone;
    @FXML
    private ComboBox<?> emp_position;
    @FXML
    private Button btnclear;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnadd;
    @FXML
    private ImageView emp_image;
    @FXML
    private Button btnimage;
    @FXML
    private AnchorPane salary_form;
    @FXML
    private TextField sl_empid;
    @FXML
    private TextField sl_salary;
    @FXML
    private Button clearbtn;
    @FXML
    private Button updatebtn;
    @FXML
    private TextField sl_empfirstname;
    @FXML
    private TextField sl_emplastname;
    @FXML
    private TextField sl_empposition;
    @FXML
    private TableColumn<EmpData, String> col_id;
    @FXML
    private TableColumn<EmpData, String> col_firstname;
    @FXML
    private TableColumn<EmpData, String> col_lastname;
    @FXML
    private TableColumn<EmpData, String> col_position;
    @FXML
    private TableColumn<EmpData, String> col_salary;
    
    
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement prs;
    
    private Image imgae;
    
    Date date = new Date();
    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    
    
    @SuppressWarnings("empty-statement")

    public  ObservableList<EmpData> addEmployeeshowlisdata(){
        ObservableList<EmpData> lishdata = FXCollections.observableArrayList();
        
       String sql = "SELECT * FROM employeedata";
       
       con = database.Database();
        try {
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();
            
            EmpData employeeData;
            
            while (rs.next()) {
                employeeData = new EmpData(
                rs.getInt("empid"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getString("gender"),
                rs.getString("phone"),
                rs.getString("address"),
                rs.getString("position"),
                rs.getString("image"),
                rs.getDate("date")); 
                
              lishdata.add(employeeData);
                
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       return lishdata;
    }
//    Adddata for form addemployee add
    
    public  void addemployeeadd(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        String sql = "INSERT INTO employeedata"
                + "(empid,firstname,lastname,gender,phone,address,position,image,date)"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
               
        con = database.Database();
        
        try {
            Alert alert;
            if(
               emp_id.getText().isEmpty()
               || emp_firstname.getText().isEmpty()
               || emp_lastname.getText().isEmpty()
               || emp_gender.getSelectionModel().getSelectedItem() == null
               || emp_phone.getText().isEmpty()
               || emp_adress.getText().isEmpty()
               || emp_position.getSelectionModel().getSelectedItem()== null
               || getData.part == null || getData.part==""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
            else{
               String check = "SELECT empid FROM employeedata WHERE empid = '" + emp_id.getText() + "'";
               
               st = con.createStatement();
               rs = st.executeQuery(check);
                
               if(rs.next()){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + emp_id.getText() + " was already exist!");
                    alert.showAndWait();
               }
               else{
                   prs = con.prepareStatement(sql);

                   prs.setString(1, emp_id.getText());
                   prs.setString(2, emp_firstname.getText());
                   prs.setString(3, emp_lastname.getText());
                   prs.setString(4, (String) emp_gender.getSelectionModel().getSelectedItem());
                   prs.setString(5, emp_phone.getText());
                   prs.setString(6, emp_adress.getText());
                   prs.setString(7, (String) emp_position.getSelectionModel().getSelectedItem());

                   String url = getData.part;
                   url = url.replace("\\", "\\\\");

                   prs.setString(8, url);
                   prs.setString(9, String.valueOf(sqlDate));
                   prs.executeUpdate();
                   
//               when you insert Data from Employee this script inseert to Salary form 

                String insert_info  ="INSERT INTO employee_info "
                            + "(Empid,firstname,lastname,position,salary,date)"
                            + "VALUES(?,?,?,?,?,?)";
                   
                prs = con.prepareStatement(insert_info);
                   prs.setString(1, emp_id.getText());
                   prs.setString(2, emp_firstname.getText());
                   prs.setString(3, emp_lastname.getText());
                   prs.setString(4, (String) emp_position.getSelectionModel().getSelectedItem());
                   prs.setString(5, "0.0");
                   prs.setString(6, String.valueOf(sqlDate));
                   prs.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Insert Data SuccessFully!");
                alert.showAndWait();
                
                addeemployyShowData();
                AddemployeeRest();
               }
               
            }
            
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    
//    Code For Update Data from Table Employee
    
    public  void addEmployeeUpdate(){
        String url = getData.part;
        url = url.replace("\\", "\\\\");
        
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        String sql = "UPDATE employeedata SET firstname = '"
                + emp_firstname.getText() + "',lastname = '"
                + emp_lastname.getText() + "' ,gender = '"
                + emp_gender.getSelectionModel().getSelectedItem()+ "',phone = '"
                + emp_phone.getText() + "',address = '" + emp_adress.getText()+ "',position = '"
                + emp_position.getSelectionModel().getSelectedItem()+ "',image = '"
                + url + "',date='"+ sqlDate + "'WHERE empid = '" + emp_id.getText() + "'";
                 
               con = database.Database();
               
               try {
                   Alert alert;
             
                if( emp_id.getText().isEmpty()
                    || emp_firstname.getText().isEmpty()
                    || emp_lastname.getText().isEmpty()
                    || emp_gender.getSelectionModel().getSelectedItem() == null
                    || emp_phone.getText().isEmpty()
                    || emp_adress.getText().isEmpty()
                    || emp_position.getSelectionModel().getSelectedItem()== null
                    || getData.part == null || getData.part==""){
                     alert = new Alert(AlertType.ERROR);
                     alert.setTitle("Error Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Please fill all blank fields");
                     alert.showAndWait();
                   }
                else{
                    alert = new Alert(AlertType.CONFIRMATION);
                     alert.setTitle("Error Message");
                     alert.setHeaderText(null);
                     alert.setContentText("Are You sure want to Update This Employee ID :" + emp_id.getText() + "?");
                     Optional<ButtonType> option = alert.showAndWait();
                     
                     if(option.get().equals(ButtonType.OK)){
                         st = con.createStatement();
                         st.executeUpdate(sql);
                         
                         
                         double salary = 0;

                    String checkData = "SELECT * FROM employee_info WHERE empid = '"
                            + emp_id.getText() + "'";

                    prs = con.prepareStatement(checkData);
                    rs = prs.executeQuery();

                    while (rs.next()) {
                        salary = rs.getDouble("salary");
                    }

                    String updateInfo = "UPDATE employee_info SET firstName = '"
                            + emp_firstname.getText() + "', lastName = '"
                            + emp_lastname.getText() + "', position = '"
                            + emp_position.getSelectionModel().getSelectedItem()
                            + "' WHERE empid = '"
                            + emp_id.getText() + "'";

                            prs = con.prepareStatement(updateInfo);
                            prs.executeUpdate();
                         
                         
                         alert = new Alert(AlertType.INFORMATION);
                         alert.setTitle("Information Message");
//                         alert.setHeaderText(null);
                         alert.setContentText("Data can Update SueecssFully!");
                         alert.showAndWait();
                         
                         addeemployyShowData();
                         AddemployeeRest();
                     }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public  void addEmployeeDelete(){
        String sql = "DELETE FROM employeedata WHERE empid = '" + emp_id.getText()+ "'";
        
        con = database.Database();
        try {
            Alert alert;
            if( emp_id.getText().isEmpty() ){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + emp_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    st = con.createStatement();
                    st.executeUpdate(sql);
                    
                    String deleteInfo = "DELETE FROM employee_info WHERE empid = '"
                            + emp_id.getText() + "'";
                    
                    prs = con.prepareStatement(deleteInfo);
                    prs.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    addeemployyShowData();
                    AddemployeeRest();
                }
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
//    Code Update salary info 
    public void salaryUpdate() {

        String sql = "UPDATE employee_info SET salary = '" + sl_salary.getText()
                + "' WHERE empid = '" + emp_id.getText() + "'";

        con = database.Database();

        try {
            Alert alert;

            if (sl_empid.getText().isEmpty()
                    || sl_empfirstname.getText().isEmpty()
                    || sl_emplastname.getText().isEmpty()
                    || sl_empposition.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select item first");
                alert.showAndWait();
            } else {
                st = con.createStatement();
                st.executeUpdate(sql);

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                salaryShowListData();
                salaryReset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
//    Employee for show data on table 
     private ObservableList<EmpData> addEmployeelish;
    public void addeemployyShowData(){
        addEmployeelish = addEmployeeshowlisdata();
        
        emp_colid.setCellValueFactory(new PropertyValueFactory<>("empid"));
        emp_colfirstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        emp_colastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emp_colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        emp_colphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emp_coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        emp_col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        emp_coldatsmeber.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        emp_tableview.setItems(addEmployeelish);
        
        
    }
    
    public  void addEmployeeselect(){
        EmpData employeedata = emp_tableview.getSelectionModel().getSelectedItem();
        int num = emp_tableview.getSelectionModel().getSelectedIndex();
        
        if((num - 1) <-1){
            
            return;
        }
        emp_id.setText(String.valueOf(employeedata.getEmpid()));
        emp_firstname.setText(String.valueOf(employeedata.getFirstname()));
        emp_lastname.setText(String.valueOf(employeedata.getLastname()));
        
        emp_phone.setText(String.valueOf(employeedata.getPhone()));
        emp_adress.setText(String.valueOf(employeedata.getAddress()));
        
        getData.part = employeedata.getImage();
         
        String url = "file:" +employeedata.getImage();
         
        imgae = new Image(url,113,107,false,true);
        emp_image.setImage(imgae);
        
        
        
    }
  
//  funtion for search data employeee 
    
     public void addEmployeeSearch() {

        FilteredList<EmpData> filter = new FilteredList<>(addEmployeelish, e -> true);

        emp_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateEmployeeData.getEmpid().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getAddress().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhone().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else return predicateEmployeeData.getDate().toString().contains(searchKey);
            });
        });

        SortedList<EmpData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(emp_tableview.comparatorProperty());
        emp_tableview.setItems(sortList);
    }
//     for  Restet salary 
     public void salaryReset(){
         sl_empid.setText("");
         sl_empfirstname.setText("");
         sl_emplastname.setText("");
         sl_empposition.setText("");
         sl_salary.setText("");
     }
     
     public  ObservableList<EmpData> salaryListData(){
      ObservableList<EmpData> listData = FXCollections.observableArrayList();
      String sql = "SELECT * FROM employee_info";
      
      con = database.Database();
             
         try {
             prs = con.prepareStatement(sql);
             rs = prs.executeQuery();
             
             EmpData employeeData;
             
             while (rs.next()){
                employeeData = new EmpData(rs.getInt("empid")
                        ,rs.getString("firstname")
                        ,rs.getString("lastname")
                        ,rs.getString("position")
                        ,rs.getDouble("salary"));
                 listData.add(employeeData);
                 
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return  listData;
     }
//   funtion show salary data 
     
     private ObservableList<EmpData>Listsalry;
     
     public void salaryShowListData(){
        Listsalry = salaryListData();
        col_id.setCellValueFactory(new PropertyValueFactory<>("empid"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        table_salary.setItems(Listsalry);
     }
    public void salarySelect() {

        EmpData employeeD = table_salary.getSelectionModel().getSelectedItem();
        int num = table_salary.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        sl_empid.setText(String.valueOf(employeeD.getEmpid()));
        sl_empfirstname.setText(employeeD.getFirstname());
        sl_emplastname.setText(employeeD.getLastname());
        sl_empposition.setText(employeeD.getPosition());
        sl_salary.setText(String.valueOf(employeeD.getSalary()));

    } 
//    Home Chart Show Staff 
    public void homeChart() {

        home_chart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM employeedata GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        con = database.Database();

        try {
            XYChart.Series chart = new XYChart.Series();

            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();

            while (rs.next()) {
                chart.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }

            home_chart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
    
//    Home Totale Eployee
      public void homeTotalEmployees() {

        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";

        con = database.Database();
        int countData = 0;
        try {

            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();

            while (rs.next()) {
                countData = rs.getInt("COUNT(id)");
            }

            home_totalactiveemp.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
//   Toatal heomeEployeePresent 
    
     public void homeEmployeeTotalPresent() {

        String sql = "SELECT COUNT(id) FROM employee_info";

        con = database.Database();
        int countData = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                countData = rs.getInt("COUNT(id)");
            }
            homepresent.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    
//    Total Active For Staff 
    public void homeTotalInactive() {

        String sql = "SELECT COUNT(id) FROM employee_info WHERE salary = '0.0'";

        con = database.Database();
        int countData = 0;
        try {
            prs = con.prepareStatement(sql);
            rs = prs.executeQuery();

            while (rs.next()) {
                countData = rs.getInt("COUNT(id)");
            }
            homr_totalemp.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
// this code for reset Addemployee 
    public void AddemployeeRest(){
        emp_id.setText("");
        emp_firstname.setText("");
        emp_lastname.setText("");
        emp_gender.getSelectionModel().clearSelection();
        emp_phone.setText("");
        emp_adress.setText("");
        emp_position.getSelectionModel().clearSelection();
        emp_image.setImage(null);
        getData.part = "";
        emp_search.setText("");
    }
    

    private String[] positionList = {"Accounting","Web Developer","Java Developer","Mobile App","Wen Apllication"};
    
    public  void addpositionList(){
        List<String> lisp = new ArrayList<>();
        
       for(String Data: positionList){
           lisp.add(Data);
       }
        ObservableList listData = FXCollections.observableArrayList(lisp);
        emp_position.setItems(listData);
    }
    
     private String[] addgenderList = {"Male","FeMale"};
    
    public  void addgenderList(){
        List<String> lisg = new ArrayList<>();
        
       for(String Data: addgenderList){
           lisg.add(Data);
       }
        ObservableList listData = FXCollections.observableArrayList(lisg);
        emp_gender.setItems(listData);
               
        
    }
    public void AddEmpImage(){
        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null){
            
            try {
                getData.part = file.getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            imgae = new Image(file.toURI().toString(),113,107,false,true);
            emp_image.setImage(imgae);
        }
    }
    
    public  void displayUsername(){
        username.setText(getData.Username);
    }
    
    public  void switForm(ActionEvent event){
       if(event.getSource()==homebtn){
           home_form.setVisible(true);
           employee_form.setVisible(false);
           salary_form.setVisible(false);
           
           homeTotalInactive();
           
       }
       else if(event.getSource() == addbtn){
           home_form.setVisible(false);
           employee_form.setVisible(true);
           salary_form.setVisible(false);
          
           addpositionList();
           addgenderList();
           addEmployeeSearch();
       }
       else if(event.getSource()==salarybtn){
           home_form.setVisible(false);
           employee_form.setVisible(false);
           salary_form.setVisible(true);
           
//           salaryReset();
           salaryShowListData();
       }
    }
    
    public  void logout(){
        Alert alert =new  Alert(AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation Massege");
        alert.setHeaderText(null);
        alert.setContentText("Are You Sure When You Logout?");
        Optional<ButtonType> option = alert.showAndWait();
        
        try {
            if(option.get().equals(ButtonType.OK)){
                outupbtn.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/javafxapp/Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
               
            }
        } catch (Exception e) {
        }
        
    }
    
    public  void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homeTotalInactive(); 
        homeEmployeeTotalPresent();
        homeTotalEmployees();
        displayUsername();
        homeChart();
        addeemployyShowData();
        addpositionList();
        addgenderList();
        
//        salaryShowListData();
        // TODO
    }    

    @FXML
    private void close(ActionEvent event) {
    }

    @FXML
    private void minimize(ActionEvent event) {
    }

    @FXML
    private void outupbtn(ActionEvent event) {
    }
    
}
