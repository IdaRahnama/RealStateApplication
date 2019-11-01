
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author idado
 */
public class P_TYPE {
    
    private int id;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public P_TYPE() {
    }

    public P_TYPE(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public P_TYPE(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    
    // a function to execute queries
    
    public boolean execTypeQuery(String queryType,P_TYPE type){
            
        PreparedStatement ps;
        
        // add a new type
        
        if(queryType.equals("add")){
        
            try {
                ps = THE_CONNECTION.getTheConnection().prepareStatement("INSERT INTO `property_type`( `name`, `description`) VALUES (?,?)");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());

                return (ps.executeUpdate() > 0);
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }
        else if(queryType.equals("edit")){
        
            try {
                // query -> UPDATE `property_type` SET `name`=? ,`description`=? WHERE `id`=?
                ps = THE_CONNECTION.getTheConnection().prepareStatement("UPDATE `property_type` SET `name`=? ,`description`=? WHERE `id`=?");
                ps.setString(1, type.getName());
                ps.setString(2, type.getDescription());
                ps.setInt(3, type.getId());

                return (ps.executeUpdate() > 0);
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }
                else if(queryType.equals("remove")){
        
            try {
                // query -> DELETE FROM `property_type` WHERE `id`=?
                ps = THE_CONNECTION.getTheConnection().prepareStatement("DELETE FROM `property_type` WHERE `id`=?");

                ps.setInt(1, type.getId());

                return (ps.executeUpdate() > 0);
            } catch (SQLException ex) {
                Logger.getLogger(P_TYPE.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }
        
                else{
                    JOptionPane.showMessageDialog(null, "ENTER (Add, Remove or Edit)", "INVALID INPUT", id);
                    return false;
                }
    }
    
}
