import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class JavaFitness {
    static String currentUser = null;
    static void showLoginFrame() {
        JFrame loginFrame = new JFrame("Login / Register");
        loginFrame.setSize(400, 250);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 30, 100, 25);
        loginFrame.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 30, 180, 25);
        loginFrame.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 70, 100, 25);
        loginFrame.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 70, 180, 25);
        loginFrame.add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(50, 120, 120, 30);
        loginFrame.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(210, 120, 120, 30);
        loginFrame.add(registerButton);

        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(50, 160, 300, 25);
        loginFrame.add(statusLabel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                    String line;
                    boolean found = false;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 2 && parts[0].equals(user) && parts[1].equals(pass)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        currentUser = user;
                        loginFrame.dispose();
                        showMainFrame();
                    } else {
                        statusLabel.setText("Invalid credentials.");
                    }
                } catch (IOException ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                }
            }
        });



        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText().trim();
                String pass = new String(passField.getPassword()).trim();
                if (!user.isEmpty() && !pass.isEmpty()) {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
                        bw.write(user + "," + pass + "\n");
                        statusLabel.setText("Registration successful.");
                    } catch (IOException ex) {
                        statusLabel.setText("Error: " + ex.getMessage());
                    }
                } else {
                    statusLabel.setText("Fill both fields.");
                }
            }
        });

        loginFrame.setVisible(true);
    }

  static void showMainFrame() {
    JFrame frame = new JFrame("Fitness Tracker");
    frame.setSize(650, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);
    frame.setLocationRelativeTo(null);

    JTextArea outputArea = new JTextArea();
    outputArea.setBounds(20, 300, 600, 340);
    frame.add(outputArea);

    JLabel activityLabel = new JLabel("Activity:");
    activityLabel.setBounds(20, 20, 100, 25);
    frame.add(activityLabel);

    JTextField activityField = new JTextField();
    activityField.setBounds(100, 20, 150, 25);
    frame.add(activityField);

    JLabel durationLabel = new JLabel("Duration (min):");
    durationLabel.setBounds(270, 20, 100, 25);
    frame.add(durationLabel);

    JTextField durationField = new JTextField();
    durationField.setBounds(370, 20, 100, 25);
    frame.add(durationField);

    JLabel distanceLabel = new JLabel("Distance (km):");
    distanceLabel.setBounds(20, 60, 100, 25);
    frame.add(distanceLabel);

    JTextField distanceField = new JTextField();
    distanceField.setBounds(120, 60, 100, 25);
    frame.add(distanceField);

    JButton logActivity = new JButton("Log Activity");
    logActivity.setBounds(240, 60, 150, 25);
    frame.add(logActivity);
    
    JButton viewActivityButton = new JButton("View Activities");
    viewActivityButton.setBounds(510, 10, 120, 30);
    frame.add(viewActivityButton);
    
    JLabel workoutLabel = new JLabel("Workout:");
    workoutLabel.setBounds(20, 100, 100, 25);
    frame.add(workoutLabel);

    JTextField workoutField = new JTextField();
    workoutField.setBounds(100, 100, 200, 25);
    frame.add(workoutField);

    JButton logWorkout = new JButton("Log Workout");
    logWorkout.setBounds(320, 100, 150, 25);
    frame.add(logWorkout);
    
    JButton viewWorkoutButton = new JButton("View Workouts");
    viewWorkoutButton.setBounds(510, 40, 120, 30);
    frame.add(viewWorkoutButton);

    JLabel foodLabel = new JLabel("Food:");
    foodLabel.setBounds(20, 140, 100, 25);
    frame.add(foodLabel);

    JTextField foodField = new JTextField();
    foodField.setBounds(100, 140, 150, 25);
    frame.add(foodField);

    JLabel calLabel = new JLabel("Calories:");
    calLabel.setBounds(270, 140, 100, 25);
    frame.add(calLabel);

    JTextField calField = new JTextField();
    calField.setBounds(350, 140, 100, 25);
    frame.add(calField);

    JButton logMeal = new JButton("Log Meal");
    logMeal.setBounds(470, 140, 100, 25);
    frame.add(logMeal);

    JLabel goalLabel = new JLabel("Goal:");
    goalLabel.setBounds(20, 180, 100, 25);
    frame.add(goalLabel);

    JTextField goalField = new JTextField();
    goalField.setBounds(100, 180, 200, 25);
    frame.add(goalField);

    JButton setGoal = new JButton("Set Goal");
    setGoal.setBounds(320, 180, 150, 25);
    frame.add(setGoal);
    
    JButton viewMealButton = new JButton("View Meals");
    viewMealButton.setBounds(510, 70, 120, 30);
    frame.add(viewMealButton);

    JButton viewGoalButton = new JButton("View Goals");
    viewGoalButton.setBounds(510, 100, 120, 30);
    frame.add(viewGoalButton);

    JButton viewAll = new JButton("View All Logs");
    viewAll.setBounds(200, 220, 150, 30);
    frame.add(viewAll);

    JButton logoutButton = new JButton("Logout");
    logoutButton.setBounds(400, 220, 150, 30);
    frame.add(logoutButton);

    logoutButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            frame.dispose();         
            currentUser = null;      
            showLoginFrame();      
        }
    });

    logActivity.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("activity.txt", true))) {
                int duration = Integer.parseInt(durationField.getText().trim());
                int calories = duration * 5;
                bw.write(currentUser + "," + activityField.getText() + "," + durationField.getText() + "min," + distanceField.getText() + "km," + calories + "cal\n");
                outputArea.setText("Activity logged.");
			activityField.setText("");
            durationField.setText("");
            distanceField.setText("");
            } catch (IOException | NumberFormatException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    });
    
   viewActivityButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try (BufferedReader br = new BufferedReader(new FileReader("activity.txt"))) {
            StringBuilder sb = new StringBuilder("Your Activities:\n");
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(currentUser + ",")) {
                    sb.append(count).append(". ").append(line).append("\n");
                    count++;
                }
            }
            if (count == 1) sb.append("No activities found.\n");
            outputArea.setText(sb.toString());
          } catch (IOException ex) {
            outputArea.setText("Error: " + ex.getMessage());
         }
     }
  });



    logWorkout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("workout.txt", true))) {
                bw.write(currentUser + "," + workoutField.getText() + "\n");
                outputArea.setText("Workout logged.");
			workoutField.setText("");
            } catch (IOException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    });

    viewWorkoutButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try (BufferedReader br = new BufferedReader(new FileReader("workout.txt"))) {
            StringBuilder sb = new StringBuilder("Your Workouts:\n");
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(currentUser + ",")) {
                    sb.append(count).append(". ").append(line).append("\n");
                    count++;
                }
            }
            if (count == 1) sb.append("No workouts found.\n");
            outputArea.setText(sb.toString());
             } catch (IOException ex) {
            outputArea.setText("Error: " + ex.getMessage());
         }
       } 
    });


    logMeal.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("nutrition.txt", true))) {
                bw.write(currentUser + "," + foodField.getText() + "," + calField.getText() + "cal\n");
                outputArea.setText("Meal logged.");
		     foodField.setText("");
             calField.setText("");
            } catch (IOException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    });

    viewMealButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try (BufferedReader br = new BufferedReader(new FileReader("nutrition.txt"))) {
            StringBuilder sb = new StringBuilder("Your Meals:\n");
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(currentUser + ",")) {
                    sb.append(count).append(". ").append(line).append("\n");
                    count++;
                }
            }
            if (count == 1) sb.append("No meals found.\n");
            outputArea.setText(sb.toString());
        } catch (IOException ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
       }
   });


    setGoal.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("goals.txt", true))) {
                bw.write(currentUser + "," + goalField.getText() + "\n");
                outputArea.setText("Goal set.");
				goalField.setText("");
            } catch (IOException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    });

     viewGoalButton.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
         try (BufferedReader br = new BufferedReader(new FileReader("goals.txt"))) {
            StringBuilder sb = new StringBuilder("Your Goals:\n");
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(currentUser + ",")) {
                    sb.append(count).append(". ").append(line).append("\n");
                    count++;
                }
            }
            if (count == 1) sb.append("No goals found.\n");
            outputArea.setText(sb.toString());
        } catch (IOException ex) {
            outputArea.setText("Error: " + ex.getMessage());
        }
    }
  });



    viewAll.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StringBuilder sb = new StringBuilder("Logs:\n");
            try {
                sb.append("--- Activities ---\n").append(readLogs("activity.txt"));
                sb.append("--- Workouts ---\n").append(readLogs("workout.txt"));
                sb.append("--- Nutrition ---\n").append(readLogs("nutrition.txt"));
                sb.append("--- Goals ---\n").append(readLogs("goals.txt"));
                outputArea.setText(sb.toString());
            } catch (IOException ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
         }
      });

    frame.setVisible(true);
 }


    static  String readLogs(String fileName) throws IOException {
    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        int count = 1;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(currentUser + ",")) {
                sb.append(count).append(". ").append(line).append("\n");
                count++;
            }
        }
        if (count == 1) {
            sb.append("No entries found.\n");
        }
    }
    return sb.toString();
  }

	    public static void main(String[] args) {     
                showLoginFrame();
    }
}
