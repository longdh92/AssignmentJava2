/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Lien
 */
public class Assignment extends javax.swing.JFrame {

    List<Employee> list = new ArrayList<>();
    int index;
    final JFileChooser fileChooser = new JFileChooser();

    /**
     * Creates new form Assignment
     */
    public Assignment() {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        initComponents();
        setLocationRelativeTo(null);
        index = tbl.getSelectedRow();
        clock();
    }

    public String salaryFormat(double salary) {
        Locale locale = new Locale("vi", "VN");
        DecimalFormat format = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(locale);
        formatSymbols.setCurrencySymbol("VND");
        format.setDecimalFormatSymbols(formatSymbols);
        return format.format(salary);
    }

    public void mouseHeaderClicked(MouseEvent event) {
        List<Employee> list2 = new ArrayList<Employee>();
        for (Employee nv : list) {
            list2.add(nv);
        }
        boolean check = true;
        int column = tbl.columnAtPoint(new Point(event.getPoint()));
        if (column == 0) {
            Comparator<Employee> com = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.id.compareTo(o2.id);
                }
            };
            Collections.sort(list, com);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == list2.get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                Collections.sort(list, com.reversed());
            }
        } else if (column == 1) {
            Comparator<Employee> com = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.name.compareTo(o2.name);
                }
            };
            Collections.sort(list, com);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == list2.get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                Collections.sort(list, com.reversed());
            }
        } else if (column == 2) {
            Comparator<Employee> com = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    Integer age1 = o1.age;
                    Integer age2 = o2.age;
                    return age1.compareTo(age2);
                }
            };
            Collections.sort(list, com);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == list2.get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                Collections.sort(list, com.reversed());
            }
        } else if (column == 3) {
            Comparator<Employee> com = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    return o1.email.compareTo(o2.email);
                }
            };
            Collections.sort(list, com);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == list2.get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                Collections.sort(list, com.reversed());
            }
        } else if (column == 4) {
            Comparator<Employee> com = new Comparator<Employee>() {
                @Override
                public int compare(Employee o1, Employee o2) {
                    Double salary1 = o1.salary;
                    Double salary2 = o2.salary;
                    return salary1.compareTo(salary2);
                }
            };
            Collections.sort(list, com);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == list2.get(i)) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }
            if (check == true) {
                Collections.sort(list, com.reversed());
            }
        }
        fillToTable();
    }

    public void clock() {
        Thread clock = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        SimpleDateFormat formater = new SimpleDateFormat();
                        formater.applyPattern("hh:mm aa");
                        String time = formater.format(new Date());
                        lbl_time.setText(time);
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public void scroll() {
        index = tbl.getSelectedRow();
        Rectangle cellRect = tbl.getCellRect(index, 0, false);
        tbl.scrollRectToVisible(cellRect);
    }

//    public void scrollMax(){
//        jScrollPane3.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
//
//            @Override
//            public void adjustmentValueChanged(AdjustmentEvent e) {
//                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//            }
//        });
//    }
    public void resetBackground() {
        txt_id.setBackground(Color.WHITE);
        txt_name.setBackground(Color.WHITE);
        txt_age.setBackground(Color.WHITE);
        txt_mail.setBackground(Color.WHITE);
        txt_salary.setBackground(Color.WHITE);
    }

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        for (Employee nv : list) {
            Object[] row = new Object[]{
                nv.id, nv.name, nv.age, nv.email, salaryFormat(nv.salary)
            };
            model.addRow(row);
        }
    }

    public void showDetail() {
        if (list.size() > 0) {
            index = tbl.getSelectedRow();
            Employee nv = list.get(index);

            txt_id.setText(nv.id);
            txt_name.setText(nv.name);
            txt_age.setText(String.valueOf(nv.age));
            txt_mail.setText(nv.email);
            txt_salary.setText(String.valueOf(salaryFormat(nv.salary)));
        }
    }

    public void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            list = (List<Employee>) InputOutput.readObject(fileChooser.getSelectedFile().getPath());
        } else {
            return;
        }
        fillToTable();
    }

    public void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            InputOutput.writeObject(fileChooser.getCurrentDirectory().toString()
                    + "\\" + fileChooser.getSelectedFile().getName(), list);
            System.exit(0);
        } else {
            return;
        }
    }

    public void addEmployee() {
        Validate vd = new Validate();
        String id1 = txt_id.getText().trim();
        String name1 = txt_name.getText().trim();
        String age1 = txt_age.getText().trim();
        String email1 = txt_mail.getText().trim();
        String salary1 = txt_salary.getText().trim();
        int k = salary1.indexOf(" ");
        if (k == -1) {
            k = salary1.length();
        }
        salary1 = salary1.substring(0, k).replace(".", "").replace(",", "");
        boolean check_id = true;
        boolean check_name = true;
        boolean check_age = true;
        boolean check_email = true;
        boolean check_salary = true;

        if (id1.length() == 0 || name1.length() == 0 || age1.length() == 0 || email1.length() == 0 || salary1.length() == 0) {
            String id2 = "";
            String name2 = "";
            String age2 = "";
            String email2 = "";
            String salary2 = "";
            if (id1.length() == 0) {
                id2 = "[Mã nhân viên] ";
            }
            if (name1.length() == 0) {
                name2 = "[Họ và tên] ";
            }
            if (age1.length() == 0) {
                age2 = "[Tuổi] ";
            }
            if (email1.length() == 0) {
                email2 = "[Email] ";
            }
            if (salary1.length() == 0) {
                salary2 = "[Lương] ";
            }
            JOptionPane.showMessageDialog(this, "Không để trống ô nhập: " + id2 + name2 + age2 + email2 + salary2);
        }
        // Kiểm tra ID
        check_id = vd.valiDateID(id1, list, -1);
        if (check_id == false) {
            txt_id.setText("");
            txt_id.setBackground(Color.YELLOW);
        }
        // Kiểm tra tên
        check_name = vd.valiDateName(name1, list);
        if (check_name == false) {
            txt_name.setText("");
            txt_name.setBackground(Color.YELLOW);
        }
        // Kiểm tra tuổi
        check_age = vd.valiDateAge(age1, list);
        if (check_age == false) {
            txt_age.setText("");
            txt_age.setBackground(Color.YELLOW);
        }
        // Kiểm tra email
        check_email = vd.valiDateMail(email1, list);
        if (check_email == false) {
            txt_mail.setText("");
            txt_mail.setBackground(Color.YELLOW);
        }
        // Kiểm tra lương
        check_salary = vd.valiDateSalary(salary1, list);
        if (check_salary == false) {
            txt_salary.setText("");
            txt_salary.setBackground(Color.YELLOW);
        }

        // Add
        Employee nv = new Employee();
        if (check_id && check_name && check_age && check_email && check_salary) {
            nv.id = txt_id.getText();
            nv.name = txt_name.getText();
            nv.age = Integer.parseInt(txt_age.getText());
            nv.email = txt_mail.getText();
            nv.salary = Double.parseDouble(salary1);
            list.add(nv);
        }
        fillToTable();
        if (check_id && check_name && check_age && check_email && check_salary) {
            index = list.indexOf(nv);
            tbl.setRowSelectionInterval(index, index);
            scroll();
        }
    }

    public void updateEmployee() {
        Validate vd = new Validate();
        String id1 = txt_id.getText().trim();
        String name1 = txt_name.getText().trim();
        String age1 = txt_age.getText().trim();
        String email1 = txt_mail.getText().trim();
        String salary1 = txt_salary.getText().trim();
        int k = salary1.indexOf(" ");
        if (k == -1) {
            k = salary1.length();
        }
        salary1 = salary1.substring(0, k).replace(".", "").replace(",", "");
        boolean check_id = true;
        boolean check_name = true;
        boolean check_age = true;
        boolean check_email = true;
        boolean check_salary = true;
        if (id1.length() == 0 || name1.length() == 0 || age1.length() == 0 || email1.length() == 0 || salary1.length() == 0) {
            String id2 = "";
            String name2 = "";
            String age2 = "";
            String email2 = "";
            String salary2 = "";
            if (id1.length() == 0) {
                id2 = "[Mã nhân viên] ";
            }
            if (name1.length() == 0) {
                name2 = "[Họ và tên] ";
            }
            if (age1.length() == 0) {
                age2 = "[Tuổi] ";
            }
            if (email1.length() == 0) {
                email2 = "[Email] ";
            }
            if (salary1.length() == 0) {
                salary2 = "[Lương] ";
            }
            JOptionPane.showMessageDialog(this, "Không để trống ô nhập: " + id2 + name2 + age2 + email2 + salary2);
        }
        // Kiểm tra ID
        check_id = vd.valiDateID(id1, list, index);
        if (check_id == false) {
            txt_id.setText("");
            txt_id.setBackground(Color.YELLOW);
        }
        // Kiểm tra tên
        check_name = vd.valiDateName(name1, list);
        if (check_name == false) {
            txt_name.setText("");
            txt_name.setBackground(Color.YELLOW);
        }
        // Kiểm tra tuổi
        check_age = vd.valiDateAge(age1, list);
        if (check_age == false) {
            txt_age.setText("");
            txt_age.setBackground(Color.YELLOW);
        }
        // Kiểm tra email
        check_email = vd.valiDateMail(email1, list);
        if (check_email == false) {
            txt_mail.setText("");
            txt_mail.setBackground(Color.YELLOW);
        }
        // Kiểm tra lương
        check_salary = vd.valiDateSalary(salary1, list);
        if (check_salary == false) {
            txt_salary.setText("");
            txt_salary.setBackground(Color.YELLOW);
        }

        // Update
        index = tbl.getSelectedRow();
        Employee nv = list.get(index);
        if (check_id && check_name && check_age && check_email && check_salary) {
            nv.id = txt_id.getText();
            nv.name = txt_name.getText();
            nv.age = Integer.parseInt(txt_age.getText());
            nv.email = txt_mail.getText();
            nv.salary = Double.parseDouble(salary1);
        }
        fillToTable();
        index = list.indexOf(nv);
        tbl.setRowSelectionInterval(index, index);
    }

    public void removeEmployee() {
        while (true) {
            String id = JOptionPane.showInputDialog("Nhập mã nhân viên cần xóa:");
            if (id == null) {
                return;
            }
            boolean check = false;
            if (id.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Không để trống mã nhân viên");
            } else {
                for (Employee nv : list) {
                    if (nv.id.equalsIgnoreCase(id)) {
                        list.remove(nv);
                        check = true;
                        break;
                    } else {
                        check = false;
                    }
                }
                if (check == true) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                    break;
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
                }
            }
        }
        fillToTable();
    }

    public void findEmployee() {
        while (true) {
            String id = JOptionPane.showInputDialog("Nhập mã nhân viên cần tìm:");
            if (id == null) {
                return;
            }
            if (id.trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Không để trống mã nhân viên");
            } else {
                boolean check = false;
                for (Employee nv : list) {
                    if (id.equalsIgnoreCase(nv.id)) {
                        txt_id.setText(nv.id);
                        txt_name.setText(nv.name);
                        txt_age.setText(String.valueOf(nv.age));
                        txt_mail.setText(nv.email);
                        txt_salary.setText(String.valueOf(salaryFormat(nv.salary)));
                        index = list.indexOf(nv);
                        tbl.setRowSelectionInterval(index, index);
                        scroll();
                        check = true;
                        break;
                    } else {
                        check = false;
                    }
                }
                if (check == false) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên");
                } else {
                    break;
                }
            }
        }
    }

    public void clearForm() {
        txt_name.setText("");
        txt_age.setText("");
        txt_id.setText("");
        txt_mail.setText("");
        txt_salary.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbl_qlnv = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        lbl_name = new javax.swing.JLabel();
        lbl_age = new javax.swing.JLabel();
        lbl_mail = new javax.swing.JLabel();
        lbl_salary = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_age = new javax.swing.JTextField();
        txt_mail = new javax.swing.JTextField();
        txt_salary = new javax.swing.JTextField();
        btn_back = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        btn_next1 = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        btn_new = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_find = new javax.swing.JButton();
        btn_open = new javax.swing.JButton();
        btn_exit = new javax.swing.JButton();
        scrollPane_tbl = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        lbl_time = new javax.swing.JLabel();
        lbl_record = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_qlnv.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lbl_qlnv.setForeground(new java.awt.Color(0, 0, 0));
        lbl_qlnv.setText("QUẢN LÝ NHÂN VIÊN");

        lbl_id.setText("MÃ NHÂN VIÊN");

        lbl_name.setText("HỌ VÀ TÊN");

        lbl_age.setText("TUỔI");

        lbl_mail.setText("EMAIL");

        lbl_salary.setText("LƯƠNG");

        btn_back.setText("|<");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_back1.setText("<<");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });

        btn_next1.setText(">>");
        btn_next1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_next1ActionPerformed(evt);
            }
        });

        btn_next.setText(">|");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_save.setText("Save");
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setText("Delete");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_find.setText("Find");
        btn_find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_findActionPerformed(evt);
            }
        });

        btn_open.setText("Open");
        btn_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openActionPerformed(evt);
            }
        });

        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_open, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_new, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_find, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(btn_exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(btn_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_delete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_find)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_open, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_exit)
                .addContainerGap())
        );

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ NHÂN VIÊN", "HỌ VÀ TÊN", "TUỔI", "EMAIL", "LƯƠNG"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMouseClicked(evt);
            }
        });
        JTableHeader header = tbl.getTableHeader();
        header.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouseHeaderClicked(evt);
            }
        });
        scrollPane_tbl.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(2).setMaxWidth(100);
        }

        lbl_time.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lbl_time.setForeground(new java.awt.Color(255, 0, 51));
        lbl_time.setText("Time");

        lbl_record.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbl_record.setForeground(new java.awt.Color(255, 0, 51));
        Timer recordList;
        ActionListener actionListener1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                index = tbl.getSelectedRow();
                if(index < 0){
                    lbl_record.setText("Record: 0 " + " of " + list.size());
                } else {
                    lbl_record.setText("Record: " + (index + 1) + " of " + list.size());
                }
            }
        };
        recordList = new Timer(1, actionListener1);
        recordList.setInitialDelay(0);
        recordList.start();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(lbl_qlnv)
                        .addGap(73, 73, 73)
                        .addComponent(lbl_time))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_id)
                            .addComponent(lbl_name)
                            .addComponent(lbl_age)
                            .addComponent(lbl_mail)
                            .addComponent(lbl_salary))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_back)
                                .addGap(6, 6, 6)
                                .addComponent(btn_back1)
                                .addGap(6, 6, 6)
                                .addComponent(btn_next1)
                                .addGap(6, 6, 6)
                                .addComponent(btn_next)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_record)))
                        .addGap(27, 27, 27)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(scrollPane_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_qlnv)
                    .addComponent(lbl_time))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_id))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_name))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_age))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_mail))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_salary))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_back)
                            .addComponent(btn_back1)
                            .addComponent(btn_next1)
                            .addComponent(btn_next)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lbl_record))))
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPane_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_next1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_next1ActionPerformed
        // TODO add your handling code here:
        if (list.size() != 0) {
            if (index < list.size() - 1) {
                index++;
            } else {
                index = 0;
            }
            tbl.setRowSelectionInterval(index, index);
            scroll();
            showDetail();
        }
    }//GEN-LAST:event_btn_next1ActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        if (list.size() != 0) {
            index = 0;
            tbl.setRowSelectionInterval(index, index);
            scroll();
            showDetail();
        }
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        // TODO add your handling code here:
        if (list.size() != 0) {
            if (index > 0) {
                index--;
            } else {
                index = list.size() - 1;
            }
            tbl.setRowSelectionInterval(index, index);
            scroll();
            showDetail();
        }

    }//GEN-LAST:event_btn_back1ActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        // TODO add your handling code here:
        if (list.size() != 0) {
            index = list.size() - 1;
            tbl.setRowSelectionInterval(index, index);
            scroll();
            showDetail();
        }
    }//GEN-LAST:event_btn_nextActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        int output = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu file?", "Thoát chương trình", JOptionPane.YES_NO_CANCEL_OPTION);
        if (output == JOptionPane.YES_OPTION) {
            saveFile();
        } else if (output == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else {
            return;
        }
    }//GEN-LAST:event_btn_exitActionPerformed

    private void btn_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openActionPerformed
        // TODO add your handling code here:
        resetBackground();
        openFile();
        if (list.size() > 0) {
            index = 0;
            tbl.setRowSelectionInterval(index, index);
            scroll();
            showDetail();
        } else {
            tbl.clearSelection();
            clearForm();
        }
    }//GEN-LAST:event_btn_openActionPerformed

    private void btn_findActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_findActionPerformed
        // TODO add your handling code here:
        resetBackground();
        findEmployee();
    }//GEN-LAST:event_btn_findActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        resetBackground();
        if (list.size() > 0) {
            removeEmployee();
            clearForm();
            tbl.clearSelection();
        } else {
            JOptionPane.showMessageDialog(this, "Không có nhân viên nào");
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        resetBackground();
        index = tbl.getSelectedRow();
        if (index == -1) {
            addEmployee();
        } else {
            if (list.size() > 0) {
                updateEmployee();
            } else {
                JOptionPane.showMessageDialog(this, "Không có nhân viên nào");
            }
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        // TODO add your handling code here:
        resetBackground();
        clearForm();
        tbl.clearSelection();
    }//GEN-LAST:event_btn_newActionPerformed

    private void tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMouseClicked
        // TODO add your handling code here:
        showDetail();
    }//GEN-LAST:event_tblMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Assignment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Assignment().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_find;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_next1;
    private javax.swing.JButton btn_open;
    private javax.swing.JButton btn_save;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public javax.swing.JLabel lbl_age;
    public javax.swing.JLabel lbl_id;
    public javax.swing.JLabel lbl_mail;
    public javax.swing.JLabel lbl_name;
    private javax.swing.JLabel lbl_qlnv;
    private javax.swing.JLabel lbl_record;
    public javax.swing.JLabel lbl_salary;
    private javax.swing.JLabel lbl_time;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane_tbl;
    private javax.swing.JTable tbl;
    protected javax.swing.JTextField txt_age;
    protected javax.swing.JTextField txt_id;
    protected javax.swing.JTextField txt_mail;
    protected javax.swing.JTextField txt_name;
    protected javax.swing.JTextField txt_salary;
    // End of variables declaration//GEN-END:variables

}
