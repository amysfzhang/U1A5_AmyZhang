/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Amy Zhang
 * Date: October 16, 2023
 * A multiple choice quiz using a Pokemon battle style
 * 
 */
import java.util.*; //read files using Scanner
import java.io.*; //catch error
        
public class Battle extends javax.swing.JFrame {

    boolean easyMode, questionMode, isEnd = false;
    int userHealth = 100, compHealth = 100, questionIndex = -1;
    String userGuess;
    
    // store questions and answers (including options)
    ArrayList<String> questions = new ArrayList<>();
    ArrayList<ArrayList<String>> answers = new ArrayList<>();
    
    /**
     * Creates new form CustomMethods
     */
    public Battle() {
        initComponents();
        
        //nothing except textarea is visible
        btnSkip.setVisible(false);
        btnToOptions.setVisible(false);
        btnSubmit.setVisible(false);
        txtGuess.setVisible(false);
        comboOptions.setVisible(false);
        //resetting mode values
        questionMode = false;
        easyMode = false;
        
        //adding questions data to arrays
        try{
            Scanner readFile = new Scanner(new File(this.getClass().getResource("Questions.csv").getFile()));

            int line = 0;
            while (readFile.hasNextLine()){
                //new scanner to read each line
                Scanner readLine = new Scanner(readFile.nextLine());
                readLine.useDelimiter(",");
                
                //storing values of a line in questions or currentAnswers
                questions.add(readLine.next());
                ArrayList<String> currentAnswers = new ArrayList<>();
                for (int i = 0; i < 4; i++){
                    currentAnswers.add(readLine.next());
                }
                answers.add(currentAnswers);
                line++;
            }
            //done reading file
            readFile.close();
            
        } catch (IOException e) {
            System.out.println("No Questions.csv file found");
            e.printStackTrace();
        }
    }
    
    /**
     * Calculates amount of damage dealt and accounts for easyMode
     * @param bound max value of the damage
     * @param easyMode lowers damage bound if true
     * @return randomized damage amount, can be 0 (a miss)
     */
    public static int damage (int bound, boolean easyMode){
        Random rand = new Random();
        int damage;
        bound +=2; //extra values for miss or critical hit
        
        //changes range to be smaller if easymode is on
        if (easyMode) bound -= 5;
        damage = rand.nextInt(bound);
        
        //slight offchance for damage to be a critical hit (more damage)
        if (damage == bound - 1){
            return bound + 3;
        }
        return damage;
    }
    
    /**
     * Displays the message if the user missed their move
     * @param damageDone is the amount of damage dealt by opponent
     */
    public void userMissesMessage(int damageDone){
        //displays message for if user missed
        txtOutput.append("\n==="
            + "\nThe correct answer was: " + answers.get(questionIndex).get(0)
            + "\n==="
            + "\nYou missed.");

        //different message depending on oppoenent damage/miss
        if (damageDone != 0) {
             txtOutput.append("The Chikorita dealt " + damageDone + " damage.");
        } else {
             txtOutput.append("The Chikorita also missed");
        }
    }
    
    /**
     * Updates progress bars with health values
     * Post-condition: updated progress bar (health) using compHealth and userHealth values
     */
    public void updateHealth(){
        compHealthBar.setValue(compHealth);
        userHealthBar.setValue(userHealth);
    }
    
    /**
     * Swaps from question mode to results or vice versa depending on current mode
     * Pre-condition: question mode is true (textfields and buttons related to answering question are visible) or question mode is false (arrow button is visible)
     * Post-condition: question mode is false (textfields and buttons related to answering question are invisible) or question mode is true (arrow button is invisible)
     */
    public void changeQMode(){
        if (!questionMode){ //showing battle results -> question asking
            btnArrow.setVisible(false); //invisible       
            
            //buttons to submit answers
            btnSubmit.setVisible(true);
            btnSkip.setVisible(true);
            //normal mode submission
            txtGuess.setVisible(true);
            lblError.setText(""); //resetting error message to empty    
            //easymode is off, reset
            btnToOptions.setVisible(true);
            btnToOptions.setEnabled(true);
            comboOptions.setVisible(false);
            easyMode = false;
            
            //updating mode
            questionMode = true;
            updateHealth();
            
        } else { //question asking -> showing battle results 
            btnArrow.setVisible(true); //only visible
            
            //setting all buttons and textfields invisible
            btnSubmit.setVisible(false);
            btnSkip.setVisible(false);
            txtGuess.setVisible(false);
            txtGuess.setText("");
            btnToOptions.setVisible(false);
            btnToOptions.setSelected(false);
            comboOptions.setVisible(false);
            
            //updating mode            
            questionMode = false;
            updateHealth();
        }
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
        jTextPane1 = new javax.swing.JTextPane();
        jSlider1 = new javax.swing.JSlider();
        jSpinner1 = new javax.swing.JSpinner();
        jTextField1 = new javax.swing.JTextField();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        userHealthBar = new javax.swing.JProgressBar();
        btnArrow = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        compHealthBar = new javax.swing.JProgressBar();
        comboOptions = new javax.swing.JComboBox<>();
        btnSkip = new javax.swing.JButton();
        txtGuess = new javax.swing.JTextField();
        btnToOptions = new javax.swing.JToggleButton();
        lblCompImage = new javax.swing.JLabel();
        lblUserImage = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTextPane1);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Battle");
        setBackground(new java.awt.Color(255, 255, 255));

        userHealthBar.setBackground(new java.awt.Color(242, 242, 242));
        userHealthBar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        userHealthBar.setForeground(new java.awt.Color(255, 255, 255));
        userHealthBar.setValue(100);
        userHealthBar.setStringPainted(true);

        btnArrow.setBackground(javax.swing.UIManager.getDefaults().getColor("Label.background"));
        btnArrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/arrow-right.png"))); // NOI18N
        btnArrow.setBorderPainted(false);
        btnArrow.setIconTextGap(0);
        btnArrow.setPreferredSize(new java.awt.Dimension(40, 50));
        btnArrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArrowActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Papyrus", 1, 36)); // NOI18N
        lblTitle.setText("Battle");

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Trebuchet MS", 0, 12)); // NOI18N
        txtOutput.setLineWrap(true);
        txtOutput.setRows(5);
        txtOutput.setText("Welcome to the battle field!\n===\nAnswer questions correctly and defeat Chikorita. \nBeware that the multiple choice is easier, but less powerful.\n===\nClick the arrow button to continue.");
        jScrollPane3.setViewportView(txtOutput);

        compHealthBar.setBackground(new java.awt.Color(242, 242, 242));
        compHealthBar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        compHealthBar.setForeground(new java.awt.Color(255, 255, 255));
        compHealthBar.setValue(100);
        compHealthBar.setStringPainted(true);

        comboOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSkip.setText("Don't know");
        btnSkip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkipActionPerformed(evt);
            }
        });

        btnToOptions.setText("Multiple Choice");
        btnToOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToOptionsActionPerformed(evt);
            }
        });

        lblCompImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chikorita.png"))); // NOI18N

        lblUserImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pikachu-color.png"))); // NOI18N

        btnSubmit.setText("Submit");
        btnSubmit.setIconTextGap(0);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnExit.setText("Go Back");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblError.setForeground(new java.awt.Color(255, 0, 51));
        lblError.setText(" ");
        lblError.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnExit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(lblTitle)
                        .addGap(193, 193, 193))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnToOptions)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblError)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSkip)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSubmit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnArrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userHealthBar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblUserImage))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCompImage, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(compHealthBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addComponent(btnExit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(compHealthBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCompImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblUserImage)
                        .addGap(18, 18, 18)
                        .addComponent(userHealthBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGuess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArrow, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSubmit)
                        .addComponent(lblError)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSkip)
                    .addComponent(btnToOptions))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArrowActionPerformed
        Random rand = new Random(); //for generating random question
        
        //continue to next question
        if (compHealth > 0 && userHealth > 0) {
            int lastQuestionIndex = questionIndex;
            do{
                questionIndex = rand.nextInt(questions.size());
            } while (questionIndex == lastQuestionIndex && questions.size() != 1);
            
            //display next question
            txtOutput.setText(questions.get(questionIndex));
            changeQMode();
            
        //player or opponent is defeated
        } else {
            //first time button is pressed (ending message)
            if (compHealth <= 0) {
                txtOutput.setText("Congrats! You have defeated Chikorita");
            } else {
                txtOutput.setText("Oh no! You have been defeated by Chikorita");
            }
            txtOutput.append("\n===\nClick the arrow button to return to the main menu");

            //second time button is pressed (goes back to main menu)
            if (isEnd == true){
                this.dispose();
                new MainMenu().setVisible(true);
            }
            isEnd = true;//differentiates first vs second button press 
        }
    }//GEN-LAST:event_btnArrowActionPerformed

    private void btnSkipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkipActionPerformed
        lblError.setText(""); //resetting error message to empty

        //dealing damage, same as getting question wrong
        int damageDone = damage(15, easyMode);        
        userMissesMessage(damageDone);//shows answer
        userHealth -= damageDone;
        
        //changes mode to results page        
        changeQMode();
    }//GEN-LAST:event_btnSkipActionPerformed

    private void btnToOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToOptionsActionPerformed
        lblError.setText(""); //resetting error message to empty
       
        //switch to easy mode, visibility change
        comboOptions.setVisible(true);
        txtGuess.setVisible(false);
        btnToOptions.setEnabled(false);
        easyMode = true;   

        //remove options in comboBos
        comboOptions.removeAllItems();

        //shuffle options in new arrayList
        ArrayList<String> shuffledOptions = new ArrayList<>();
        for (String option : answers.get(questionIndex)) shuffledOptions.add(option);
        Collections.shuffle(shuffledOptions);

        //add shuffled options to comboBox
        for (String option : shuffledOptions) comboOptions.addItem(option);
    }//GEN-LAST:event_btnToOptionsActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        lblError.setText(""); //resetting error message to empty
        
        //get input from user, differing modes
        if (easyMode){
            userGuess = String.valueOf(comboOptions.getSelectedItem()); //first selected default
        } else { //normal mode
            userGuess = txtGuess.getText().toLowerCase();
            //returns if nothing is entered
            if (userGuess.equals("")){
                lblError.setText("You must enter an answer to submit");
                return;
            }
        }

        //dealing damage
        int damageDone = damage(15, easyMode);
        
        //user answer is correct
        if (userGuess.equals(answers.get(questionIndex).get(0))){ 
            txtOutput.append("\n===\nThat was correct!\n===\n");
            
            //attacking opponent
            if (damageDone == 0) damageDone = 1; //can not miss
            compHealth -= damageDone;       
            txtOutput.append("Pikachu dealt " + damageDone + " damage. ");
            
            //opponent damaging user
            damageDone = damage(15, easyMode);
            userHealth -= damageDone;
            //different message depending of if opponent missd or hit
            if (damageDone != 0) {
                 txtOutput.append("The Chikorita dealt " + damageDone + " damage.");
            } else {
                 txtOutput.append("The Chikorita missed");
            }
            
        //user answer is incorrect
        } else {
            damageDone = damage(15, easyMode);
            userMissesMessage(damageDone);
            userHealth -= damageDone;
        }

        //changes mode to results page
        changeQMode();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        //opens main menu, closes current
        this.dispose();
        new MainMenu().setVisible(true);
    }//GEN-LAST:event_btnExitActionPerformed

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
            java.util.logging.Logger.getLogger(Battle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Battle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Battle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Battle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Battle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArrow;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSkip;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JToggleButton btnToOptions;
    private javax.swing.JComboBox<String> comboOptions;
    private javax.swing.JProgressBar compHealthBar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel lblCompImage;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserImage;
    private javax.swing.JTextField txtGuess;
    private javax.swing.JTextArea txtOutput;
    private javax.swing.JProgressBar userHealthBar;
    // End of variables declaration//GEN-END:variables
}
