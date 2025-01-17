//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

//    Instant nowUtc = Instant.now();
//    ZoneId asiaSingapore = ZoneId.of("Asia/Singapore");
    
        Calendar calendar= Calendar.getInstance();
        TimeZone timeZone= TimeZone.getDefault();
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

        JButton militaryTime;
        JButton gmt;

        boolean isMilitary = false;
        boolean isGMT = false;




        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(350, 220);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat = new SimpleDateFormat("EEEE");
            dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel = new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
    
            dateLabel = new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));

            militaryTime = new JButton("Military");
            militaryTime.addActionListener(this::militaryStandard);
            gmt = new JButton("GMT");
            gmt.addActionListener(this::gmtTimezone);


    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);
            this.add(militaryTime);
            this.add(gmt);
    
            setTimer();
        }

    public void MyTimeZoneClass() {
        // Initialize the calendar and timeZone objects
        calendar = Calendar.getInstance();
        timeZone = TimeZone.getDefault();
    }
    private void gmtTimezone(ActionEvent actionEvent) {
        if (!isGMT) {
            // Get the GMT timezone object
            TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

            // Set the timezone of the calendar and the current timezone to GMT
            calendar.setTimeZone(gmtTimeZone);
            timeZone = gmtTimeZone;

            isGMT = true;
        }
    }

    private void militaryStandard(ActionEvent actionEvent) {
       if(isMilitary){
           timeFormat = new SimpleDateFormat("hh:mm:ss a");
           isMilitary = false;
       } else {
           timeFormat = new SimpleDateFormat("HH:mm:ss a");
           isMilitary = true;
       }
    }


    public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);
    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        public static void main(String[] args) {
            new SimpleClock();
        }
    }
