/*
 * KAM Navigator Plugin
 *
 * URLs: http://openbel.org/
 * Copyright (C) 2012, Selventa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.openbel.belframework.kam.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.openbel.belframework.kam.NetworkOption;
import org.openbel.belframework.kam.Utility;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;

public final class SearchKAMListDialog extends JDialog implements
        ActionListener {
    private static final long serialVersionUID = -2555610304142946995L;

    private static final String DIALOG_TITLE = "Add KAM List";

    // swing fields
    private JButton addButton;
    private JButton browseButton;
    private JButton cancelButton;
    private JFileChooser fileChooser;
    private JTextField fileTextField;
    private JComboBox namespaceComboBox;
    private JLabel namespaceLabel;
    private JComboBox networkComboBox;
    private JLabel networkLabel;
    private JPanel resultsPanel;
    private JTable resultsTable;
    private JButton searchButton;
    private JScrollPane tableScrollPane;

    public SearchKAMListDialog() {
        super(Cytoscape.getDesktop(), DIALOG_TITLE, false);

        initUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    private void initUI() {
        initComponents();

        // additional stuff (kept separate for future UI work)
        // adjust position to default, keeps dialog from appearing offscreen
        setLocationRelativeTo(null);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonActionPerformed(e);
            }
        });

        // resultsPanel.setVisible(false);

        FileFilter fileFilter = new FileNameExtensionFilter(
                "CSV and TXT files", "csv", "txt");
        // FIXME none CSV and TXT files are still selectable
        fileChooser.addChoosableFileFilter(fileFilter); // setFileFilter(fileFilter);
        
        // network options
        final Set<CyNetwork> networkSet = Utility.getKamNetworks();
        final List<NetworkOption> networks = new ArrayList<NetworkOption>(networkSet.size());
        for (Iterator<CyNetwork> it = networkSet.iterator(); it.hasNext();) {
            CyNetwork cyn = it.next();

            NetworkOption networkOpt = new NetworkOption(cyn);
            networks.add(networkOpt);

            // trap this network option if this is the active cyn
            if (Cytoscape.getCurrentNetwork() == cyn) {
                networkComboBox.setSelectedItem(networkOpt);
            };
        }
        networkComboBox.setModel(new DefaultComboBoxModel(networks
                .toArray(new NetworkOption[networks.size()])));
        networkComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                networkComboBoxActionPerformed(e);
            }
        });
        
        // file text field
        fileTextField.setText("");
        fileTextField.setEditable(false);
        
        // model for results table
        resultsTable.setModel(new DefaultTableModel(null,
                new String[] { "Identifier", "Resolved" }));

    }

    private void browseButtonActionPerformed(ActionEvent e) {
        // FIXME according to cytoscape javadocs, should use
        // FileUtil.getFile instead of jfile chooser
        int returnVal = fileChooser.showOpenDialog(this);

        switch (returnVal) {
        case JFileChooser.APPROVE_OPTION:
            File file = fileChooser.getSelectedFile();
            fileTextField.setText(file.getName());
            
            List<String> identifiers = null;
            try {
                identifiers = readIdentifiersFromFile(file);
            } catch (IOException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
            
            if (identifiers != null && !identifiers.isEmpty()) {
                Map<String, Boolean> resolved = resolve(identifiers);
                
                DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
                for (Entry<String, Boolean> entry : resolved.entrySet()) {
                    model.addRow(new Object[] { entry.getKey(),
                            entry.getValue() });
                }
            }
            break;
        case JFileChooser.CANCEL_OPTION:
        case JFileChooser.ERROR_OPTION:
        default:
            break;
        }

    }

    private Map<String, Boolean> resolve(List<String> identifiers) {
        final Map<String, Boolean> results = new LinkedHashMap<String,Boolean>();
        // final Namespace namespace = (Namespace) namespaceComboBox.getSelectedItem();
        
        // run task to resolve each identifiers
        
        return results;
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        this.dispose();
    }

    protected void networkComboBoxActionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    protected void searchButtonActionPerformed(ActionEvent e) {
        // resultsPanel.setVisible(true);
    }

    // taken from netbeans
    // this method was taken from auto generated code, apologies if it sucks
    private void initComponents() {

        fileChooser = new JFileChooser();
        namespaceComboBox = new JComboBox();
        namespaceLabel = new JLabel();
        fileTextField = new JTextField();
        browseButton = new JButton();
        searchButton = new JButton();
        resultsPanel = new JPanel();
        addButton = new JButton();
        cancelButton = new JButton();
        tableScrollPane = new JScrollPane();
        resultsTable = new JTable();
        networkLabel = new JLabel();
        networkComboBox = new JComboBox();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add KAM List");
        setName("Add KAM List");

        namespaceComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        namespaceLabel.setText("Namespace:");

        fileTextField.setText("jTextField1");

        browseButton.setText("Browse");
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");

        addButton.setText("Add");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        tableScrollPane.setViewportView(resultsTable);

        GroupLayout resultsPanelLayout = new GroupLayout(resultsPanel);
        resultsPanel.setLayout(resultsPanelLayout);
        resultsPanelLayout.setHorizontalGroup(
            resultsPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultsPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(resultsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addGap(18, 18, 18)
                        .addComponent(addButton))
                    .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        resultsPanelLayout.setVerticalGroup(
            resultsPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(resultsPanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(tableScrollPane, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(resultsPanelLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        networkLabel.setText("Network:");

        networkComboBox.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(resultsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(fileTextField)
                        .addGap(18, 18, 18)
                        .addComponent(browseButton))
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                            .addComponent(namespaceLabel)
                            .addComponent(networkLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(namespaceComboBox, 0, 260, Short.MAX_VALUE)
                            .addComponent(networkComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(networkComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(networkLabel))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(namespaceLabel)
                    .addComponent(namespaceComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(fileTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(18, 18, 18)
                .addComponent(searchButton)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(resultsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private static List<String> readIdentifiersFromFile(final File file)
            throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> identifiers = new ArrayList<String>();
        // this is null so it can be determined by the first iteration
        Boolean csv = null;
        try {
            for (String line = null; (line = reader.readLine()) != null;) {
                if (isBlank(line)) {
                    // ignore blank lines
                    continue;
                }

                if (csv == null) {
                    csv = (line.indexOf(",") != -1);
                }

                if (csv) {
                    // if csv take first column as identifier
                    String[] split = line.split(",");
                    String firstColumn = split[0];
                    if (!isBlank(firstColumn)) { // don't add blank identifiers
                        identifiers.add(firstColumn);
                    }
                } else {
                    // else just take entire line as identifier
                    identifiers.add(line);
                }
            }
        } finally {
            Utility.closeSilently(reader);
        }
        return identifiers;
    }
    
    /**
     * Returns true if string is null, empty, or all whitespace
     */
    private static boolean isBlank(final String string) {
        if (string == null || string.isEmpty()) {
            return true;
        }
        
        for (char c : string.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }
}
