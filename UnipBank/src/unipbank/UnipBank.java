package unipbank;
import model.*;
import view.*;
import controller.*;

public class UnipBank {

    public static void main(String[] args) {
        frmcliente viewC = new frmcliente();
        clientedao modelC = new clientedao();
        controlerCRUD controleC = new controlerCRUD(viewC, modelC);
        
        viewC.setVisible(true);
        viewC.setLocationRelativeTo(null);
    }
    
}
