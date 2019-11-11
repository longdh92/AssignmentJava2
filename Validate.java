/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Lien
 */
public class Validate extends JFrame {

    public boolean valiDateID(String id1, List<Employee> list, int index) {
        boolean check = true;
        if (id1.length() == 0) {
            check = false;
        } else {
            for (Employee nv : list) {
                if (index == -1) {
                    if (nv.id.equalsIgnoreCase(id1)) {
                        JOptionPane.showMessageDialog(this, "Không được phép trùng ID");
                        check = false;
                    }
                } else {
                    if (nv != list.get(index) && nv.id.equalsIgnoreCase(id1)) {
                        JOptionPane.showMessageDialog(this, "Không được phép trùng ID");
                        check = false;
                    }
                }
            }
        }
        return check;
    }

    public boolean valiDateName(String name1, List<Employee> list) {
        boolean check = true;
        if (name1.length() == 0) {
            check = false;
        }
        return check;
    }

    public boolean valiDateAge(String age1, List<Employee> list) {
        boolean check = true;
        if (age1.length() == 0) {
            check = false;
        } else {
            try {
                int tuoi = Integer.parseInt(age1);
                if (tuoi < 16 || tuoi > 55) {
                    JOptionPane.showMessageDialog(this, "Tuổi phải từ 16 đến 55");
                    check = false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng tuổi");
                check = false;
            }
        }
        return check;
    }

    public boolean valiDateSalary(String salary1, List<Employee> list) {
        boolean check = true;
        if (salary1.length() == 0) {
            check = false;
        } else {
            try {
                double luong = Double.parseDouble(salary1);
                if (luong < 0) {
                    JOptionPane.showMessageDialog(this, "Lương phải là số dương");
                    check = false;
                } else if (luong < 5000000) {
                    JOptionPane.showMessageDialog(this, "Lương phải lớn hơn 5 triệu");
                    check = false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng lương");
                check = false;
            }
        }
        return check;
    }

    public boolean valiDateMail(String email1, List<Employee> list) {
        boolean check = true;
        if (email1.length() == 0) {
            check = false;
        } else {
            String checkMail = "\\w+@\\w+((\\.\\w+){1,2})";
            if (!email1.matches(checkMail)) {
                JOptionPane.showMessageDialog(this, "Sai định dạng mail");
                check = false;
            }
        }
        return check;
    }

//    public boolean valiDateIdUpdate(String id1, List<Employee> list, int index) {
//        boolean check = true;
//        if (id1.length() == 0) {
//            check = false;
//        } else {
//            for (Employee nv : list) {
//                if (nv != list.get(index) && nv.id.equalsIgnoreCase(id1)) {
//                    JOptionPane.showMessageDialog(this, "Không được phép trùng ID");
//                    check = false;
//                }
//            }
//        }
//        return check;
//    }
}
