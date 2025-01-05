package main.java.pertemuan14.jfc_httpclient;

import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame {
    public static void main(String[] args) {
        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom().setSoTimeout(Timeout.ofSeconds(5)).build();
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom().setIOReactorConfig(ioReactorConfig).build();
        client.start();

        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io");
        final String requestUri = "/api/contacts";

        //membuat frame utama
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh HTTP Client di swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
           frame.setLayout(new BorderLayout());

           //label untuk status
            JLabel statuslabel= new JLabel("Tekan tombol untuk mulai mengunduh data", JLabel.CENTER);

            //tombol untuk memulai proses
            JButton startButton = new JButton("Start");

            //progress bar
            JProgressBar progressBar = new JProgressBar(0, 100);

            //tambahkan komponen ke frame
            frame.add(statuslabel, BorderLayout.NORTH);

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel();
            panel.add(startButton);
            panel.add(progressBar);
            panel.setLayout(new FlowLayout());
            frame.add(panel, BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
            final SimpleHttpRequest request = SimpleRequestBuilder.get().setHttpHost(target).setPath(requestUri).build();
            startButton.addActionListener(e -> {
                progressBar.setIndeterminate(true);
                startButton.setEnabled(false); //nonaktifkan button saat proses berjalan
                statuslabel.setText("Proses berjalan...");
                textArea.setText("");
                client.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<>() {
                            @Override
                            public void completed(final SimpleHttpResponse response) {
                                System.out.println(request + "->" + new StatusLine(response));

                                try {
                                    // Parse JSON response using org.json
                                    JSONArray contacts = new JSONArray(response.getBodyText());

                                    // Update the JTextArea with contact data
                                    SwingUtilities.invokeLater(() -> {
                                        contacts.forEach(obj -> {
                                            JSONObject contact = (JSONObject) obj;
                                            String line = "Name: " + contact.getString("name") +
                                                    ", Phone: " + contact.getString("phone");
                                            textArea.append(line + "\n");
                                        });
                                        progressBar.setIndeterminate(false);
                                        startButton.setEnabled(true);
                                        statuslabel.setText("Proses selesai!");
                                    });

                                } catch (Exception e) {
                                    // Handle JSON parsing exception
                                    SwingUtilities.invokeLater(() -> {
                                        progressBar.setIndeterminate(false);
                                        startButton.setEnabled(true);
                                        statuslabel.setText("Proses gagal parsing data!");
                                    });
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(final Exception ex) {
                                System.out.println(request + "->" + ex);
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statuslabel.setText("Proses gagal!");
                            }

                            @Override
                            public void cancelled() {
                                System.out.println(request + "cancelled");
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statuslabel.setText("Proses dibatalkan");
                            }
                        });
            });
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
