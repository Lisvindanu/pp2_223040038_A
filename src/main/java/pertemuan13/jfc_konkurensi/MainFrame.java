package main.java.pertemuan13.jfc_konkurensi;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame {
    public static void main(String[] args) {
        //membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh konkurensi di Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            //label untuk status
            JLabel statusLabel = new JLabel("Tekan tombol untuk mulai tugas berat", JLabel.CENTER);

            // tombol untuk mulai proses
            JButton startButton = new JButton("Mulai");

            // Progress Bar
            JProgressBar progressBar = new JProgressBar(0, 60);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);

            //Tambahkan komponen ke frame
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(startButton, BorderLayout.SOUTH);


            //            //tombol aksi yang memberatkan proses
//            startButton.addActionListener(e -> {
//                //update progress bar 1% per detik
//                for(int i = 0; i <= 60; i++) {
//                    progressBar.setValue(i);
//                    try {
//                        Thread.sleep(1000);
//                    }catch (Exception ex) {
//                        System.err.println(ex.getMessage());
//                    }
//                }
//            });


            //tombol aksi
            startButton.addActionListener(e -> {
                startButton.setEnabled(false); // nonaktifkan tombol saat proses berjalan
                statusLabel.setText("Proses berjalan ...");
                //buat swing worker untuk menangani tugas berat
                SwingWorker<Void, Integer> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        //simulasi tugas berat
                        for (int i = 0; i <= 60; i++) {
                            Thread.sleep(50);
                            publish(i);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        //perbarui progress bar
                        int latestProgeress = chunks.get(chunks.size() - 1);
                        progressBar.setValue(latestProgeress);
                    }

                    @Override
                    protected void done() {
                        //aksi setelah tugas selesai
                        startButton.setEnabled(true);
                        statusLabel.setText("Proses selesai");
                    }
                };
                worker.execute();
            });

            frame.setVisible(true);
        });
    }
}
