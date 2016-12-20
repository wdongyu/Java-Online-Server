/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.JOptionPane;

/**
 *
 * @author zhouyichen
 */
public class DictJFrame extends javax.swing.JFrame {

    /**
     * Creates new form DictJFrame
     */
    Dict d;
    private boolean isToggleSet;
    public DictJFrame() {
        initComponents();
        jToggleButton1.setVisible(false);
        isToggleSet = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        TypeField = new javax.swing.JTextField();
        SearchButton = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jCheckBoxjs = new javax.swing.JCheckBox();
        jCheckBoxyd = new javax.swing.JCheckBox();
        jCheckBoxhc = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        HisList = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AssoField = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPanejs = new javax.swing.JScrollPane();
        jsDisplayArea = new javax.swing.JTextArea();
        jScrollPaneyd = new javax.swing.JScrollPane();
        ydDisplayArea = new javax.swing.JTextArea();
        jScrollPanehc = new javax.swing.JScrollPane();
        hcDisplayArea = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dictionary");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        TypeField.setBackground(new java.awt.Color(236, 254, 236));
        TypeField.setFont(new java.awt.Font("Lucida Sans", 2, 14)); // NOI18N
        TypeField.setText("TypeHere");
        TypeField.setPreferredSize(new java.awt.Dimension(69, 30));
        TypeField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                inputTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        TypeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TypeFieldActionPerformed(evt);
            }
        });
        TypeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldTyped(evt);
            }
        });

        SearchButton.setBackground(new java.awt.Color(255, 255, 204));
        SearchButton.setText("Search");
        SearchButton.setName(""); // NOI18N
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        jCheckBoxjs.setSelected(true);
        jCheckBoxjs.setText("金山");
        jCheckBoxjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxjsActionPerformed(evt);
            }
        });

        jCheckBoxyd.setSelected(true);
        jCheckBoxyd.setText("有道");
        jCheckBoxyd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxydActionPerformed(evt);
            }
        });

        jCheckBoxhc.setSelected(true);
        jCheckBoxhc.setText("海词");
        jCheckBoxhc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxhcActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TypeField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCheckBoxjs, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxyd, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxhc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(41, 41, 41))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxyd)
                    .addComponent(jCheckBoxhc)
                    .addComponent(jCheckBoxjs))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchButton))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(TypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
                .addContainerGap())
        );

        jCheckBoxyd.getAccessibleContext().setAccessibleDescription("");

        jLabel2.setFont(new java.awt.Font("Kefa", 3, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("History");

        HisList.setEditable(false);
        HisList.setBackground(new java.awt.Color(236, 254, 236));
        HisList.setColumns(5);
        HisList.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        HisList.setRows(4);
        HisList.setTabSize(6);
        HisList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        HisList.setDropTarget(null);
        HisList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HisListClicked(evt);
            }
        });
        jScrollPane3.setViewportView(HisList);

        jLabel3.setFont(new java.awt.Font("Kefa", 3, 18)); // NOI18N
        jLabel3.setText("Did you mean?");

        AssoField.setEditable(false);
        AssoField.setBackground(new java.awt.Color(236, 254, 236));
        AssoField.setColumns(20);
        AssoField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        AssoField.setForeground(new java.awt.Color(153, 153, 153));
        AssoField.setLineWrap(true);
        AssoField.setRows(4);
        AssoField.setTabSize(6);
        AssoField.setWrapStyleWord(true);
        AssoField.setAutoscrolls(false);
        AssoField.setBorder(null);
        AssoField.setCaretColor(new java.awt.Color(204, 204, 204));
        AssoField.setDragEnabled(false);
        AssoField.setSize(new java.awt.Dimension(30, 50));
        AssoField.setVerifyInputWhenFocusTarget(false);
        AssoField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                candidateClicked(evt);
            }
        });
        jScrollPane2.setViewportView(AssoField);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
        );

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Designed by ZhouYichen");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        //jTabbedPane1.setTabComponentAt(0, new ButtonTabComponent(jTabbedPane1));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jScrollPanejs.setPreferredSize(new java.awt.Dimension(194, 100));

        jsDisplayArea.setEditable(false);
        jsDisplayArea.setBackground(new java.awt.Color(236, 254, 236));
        jsDisplayArea.setColumns(10);
        jsDisplayArea.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        jsDisplayArea.setLineWrap(true);
        jsDisplayArea.setRows(5);
        jsDisplayArea.setText("Explanation");
        jScrollPanejs.setViewportView(jsDisplayArea);
        jsDisplayArea.setName("释义:");
        jsDisplayArea.setLineWrap(true);
        jsDisplayArea.setWrapStyleWord(true);

        jTabbedPane1.addTab("金  山", jScrollPanejs);

        ydDisplayArea.setEditable(false);
        ydDisplayArea.setBackground(new java.awt.Color(236, 254, 236));
        ydDisplayArea.setColumns(10);
        ydDisplayArea.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        ydDisplayArea.setLineWrap(true);
        ydDisplayArea.setRows(5);
        ydDisplayArea.setText("Explanation");
        jScrollPaneyd.setViewportView(ydDisplayArea);
        jsDisplayArea.setName("释义:");
        jsDisplayArea.setLineWrap(true);
        jsDisplayArea.setWrapStyleWord(true);

        jTabbedPane1.addTab("有  道", jScrollPaneyd);

        hcDisplayArea.setEditable(false);
        hcDisplayArea.setBackground(new java.awt.Color(236, 254, 236));
        hcDisplayArea.setColumns(10);
        hcDisplayArea.setFont(new java.awt.Font("Lucida Grande", 0, 20)); // NOI18N
        hcDisplayArea.setLineWrap(true);
        hcDisplayArea.setRows(5);
        hcDisplayArea.setText("Explanation");
        jScrollPanehc.setViewportView(hcDisplayArea);
        jsDisplayArea.setName("释义:");
        jsDisplayArea.setLineWrap(true);
        jsDisplayArea.setWrapStyleWord(true);

        jTabbedPane1.addTab("海  词", jScrollPanehc);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconLike copy.jpg"))); // NOI18N
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToggleButton1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jToggleButton1VetoableChange(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenu1.setText("File");
        jMenu1.setToolTipText("");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Online");

        jMenuItem1.setText("Show Online Users");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Create Vocabulary Card");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(318, 318, 318)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TypeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TypeFieldActionPerformed
        // TODO add your handling code here:
        BeginSearch();
    }//GEN-LAST:event_TypeFieldActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        // TODO add your handling code here:
        BeginSearch();
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void HisListClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HisListClicked
        // TODO add your handling code here:
        TypeField.setText(HisList.getSelectedText());
    }//GEN-LAST:event_HisListClicked

    private void fieldTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldTyped
        // TODO add your handling code here:
        //System.out.println("fieldTyped:" + TypeField.getText());
        String content = Dict.association(TypeField.getText());
        //System.out.println("content:" + content);
        if (!content.equals("")) {
            AssoField.setText(content);
        }
    }//GEN-LAST:event_fieldTyped

    private void inputTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_inputTextChanged
        // TODO add your handling code here:
        System.out.println("inputTextChanged:" + TypeField.getText());
        String content = Dict.association(TypeField.getText());
        AssoField.setText(content);
    }//GEN-LAST:event_inputTextChanged

    private void candidateClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_candidateClicked
        // TODO add your handling code here:
        TypeField.setText(AssoField.getSelectedText());
    }//GEN-LAST:event_candidateClicked

    private void jCheckBoxjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxjsActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxjs.isSelected()){
            jScrollPanejs.setVisible(true);
            jTabbedPane1.add("金山",jScrollPanejs);
        }else{
            jScrollPanejs.setVisible(false);
            jTabbedPane1.remove(jScrollPanejs);
        }
        //System.out.println("performed");

    }//GEN-LAST:event_jCheckBoxjsActionPerformed

    private void jCheckBoxhcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxhcActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxhc.isSelected()){
            jScrollPanehc.setVisible(true);
            jTabbedPane1.add("海词",jScrollPanehc);
        }else{
            jScrollPanehc.setVisible(false);
            jTabbedPane1.remove(jScrollPanehc);
        }
    }//GEN-LAST:event_jCheckBoxhcActionPerformed

    private void jCheckBoxydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxydActionPerformed
        // TODO add your handling code here:
        if (jCheckBoxyd.isSelected()){
            jScrollPaneyd.setVisible(true);
            jTabbedPane1.add("有道",jScrollPaneyd);
        }else{
            jScrollPaneyd.setVisible(false);
            jTabbedPane1.remove(jScrollPaneyd);
        }
    }//GEN-LAST:event_jCheckBoxydActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        int index = jTabbedPane1.getSelectedIndex();
        int focus = likes.get(index).code;
        System.out.println("like " + focus);
        /*
        switch (focus) {
                case 0:
                    

                    break;
                case 1:
                    jTabbedPane1.add("有  道", jScrollPaneyd);
                    break;
                case 2:
                    jTabbedPane1.add("海  词", jScrollPanehc);
                    break;
            }
        */
        String[] content = new String[3];
        content[0] = TypeField.getText();
        content[1] = Integer.toString(focus);
        if (likes.get(index).localLike){
            content[2] = "-1";
        }else{
            content[2] = "+1";
        }
        Tokens t = new Tokens(4, content);
        Dict.Send(t);
        t = Dict.Receive();
        System.out.println(jToggleButton1.getBackground().toString());
        likes.get(index).localLike = !likes.get(index).localLike;
        if (likes.get(index).localLike){
            jToggleButton1.setBackground(Color.pink);
        }else{
            jToggleButton1.setBackground(new Color(214,217,223));
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jToggleButton1StateChanged

    private void jToggleButton1VetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jToggleButton1VetoableChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1VetoableChange

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        Dict.logout();
        
    }//GEN-LAST:event_formWindowClosing

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        int index = jTabbedPane1.getSelectedIndex();
        if (likes.get(index).localLike){
            jToggleButton1.setBackground(Color.pink);
        }else{
            jToggleButton1.setBackground(new Color(214,217,223));
        }
    }//GEN-LAST:event_jTabbedPane1MouseClicked
    public static String[] userList;
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        OnlineWin listWin = new OnlineWin();
        listWin.setVisible(true);
        listWin.frame = this;
        //System.out.println("frame529 " +userList.length);
        listWin.Display(userList);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jMenu2.setPopupMenuVisible(false);
        String fileName = SnapShot();
        JOptionPane.showMessageDialog(null, "\"" + fileName + "\"\nAlready saved in local.", "SnapShot Succeed ", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_jMenuItem2ActionPerformed
    private ArrayList<Like> likes = new ArrayList();
    private class Like implements Comparable{
        public Integer number;
        public Integer code;
        public String name;
        public boolean localLike;
        public Like(int num, int name){
            this.number = num;
            this.code = name;
            this.localLike = false;
        }

        @Override
        public int compareTo(Object o) {
            Like temp = (Like)o;
            return temp.number.compareTo(this.number);
        }
    }
    private void tabSort(String[] content){
        likes.clear();
        likes.add(new Like(Integer.parseInt(content[3]), 0));
        likes.add(new Like(Integer.parseInt(content[4]), 1));
        likes.add(new Like(Integer.parseInt(content[5]), 2));
        System.out.println("likes size:" + likes.size());
        
        Collections.sort(likes);
        jTabbedPane1.removeAll();
        Iterator<Like> it = likes.iterator();
        while (it.hasNext()) {
            int x = it.next().code;
            //System.out.println(x);
            switch (x) {
                case 0:
                    jTabbedPane1.add("金  山", jScrollPanejs);
                    break;
                case 1:
                    jTabbedPane1.add("有  道", jScrollPaneyd);
                    break;
                case 2:
                    jTabbedPane1.add("海  词", jScrollPanehc);
                    break;
            }
        }
    }
    private void BeginSearch() {
        jToggleButton1.setVisible(true);
        String searchWord = TypeField.getText();
        if (searchWord.equals("")) {
            return;
        }
        System.out.println("SearchWord:" + searchWord);
        String[] temp = {searchWord};
        Dict.Send(new Tokens(3, temp));
        /*
        String localExp = Dict.Search(searchWord);
        jsDisplayArea.setText(localExp);
        ydDisplayArea.setText(localExp);
        hcDisplayArea.setText(localExp);
        System.out.println("buffer out");
        */
        Tokens result = Dict.Receive();
        System.out.println("tpye:" + result.getType());
        jsDisplayArea.setText(result.getContent()[0]);
        ydDisplayArea.setText(result.getContent()[1]);
        hcDisplayArea.setText(result.getContent()[2]);
        tabSort(result.getContent());
        HisList.insert(searchWord + "\n", 0);
    }
    public String SnapShot(){

       
        String fileName = null;
        try {
            Robot robot = new Robot();
            Rectangle area = new Rectangle(this.getBounds());
            BufferedImage bufferedImage = robot.createScreenCapture(area);

            // Write generated image to a file
            // Save as PNG
            Date date = new Date();
            fileName = new String("SnapShot_" + date.toString() + ".png");
            File file = new File(fileName);
            ImageIO.write(bufferedImage, "png", file);

        } catch (Exception e) {
            System.out.println("Could not save small snapShot " + e.getMessage());
        }
        return fileName;
    }

    /**
     * @param args the command line arguments
     */

    public void BuildFrame() {
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
            java.util.logging.Logger.getLogger(DictJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DictJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DictJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DictJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new DictJFrame().setVisible(true);
        });
    }

    

    public void getSearchWord() {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AssoField;
    private javax.swing.JTextArea HisList;
    private javax.swing.JButton SearchButton;
    private javax.swing.JTextField TypeField;
    private javax.swing.JTextArea hcDisplayArea;
    private javax.swing.JCheckBox jCheckBoxhc;
    private javax.swing.JCheckBox jCheckBoxjs;
    private javax.swing.JCheckBox jCheckBoxyd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPanehc;
    private javax.swing.JScrollPane jScrollPanejs;
    private javax.swing.JScrollPane jScrollPaneyd;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextArea jsDisplayArea;
    private javax.swing.JTextArea ydDisplayArea;
    // End of variables declaration//GEN-END:variables

}
