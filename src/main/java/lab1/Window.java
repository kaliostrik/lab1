package lab1.texteditor;

import javax.swing.*;
import java.awt.*;
import java.io.*;

class Window extends JFrame{

    final private JMenuBar menuBar = new JMenuBar();
    final private JTextArea textArea = new JTextArea();
    private File file;

    public Window() {
        super("Text Editor");
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        setJMenuBar(menuBar);
        JMenu menuFile = new JMenu("Файл");
        menuBar.add(menuFile);
        JMenuItem fileNew = new JMenuItem("Новый");
        menuFile.add(fileNew);
        fileNew.addActionListener(todo -> newFile());
        JMenuItem fileOpen = new JMenuItem("Открыть");
        menuFile.add(fileOpen);
        fileOpen.addActionListener(todo -> loadFile());

        JMenuItem fileSave = new JMenuItem("Сохранить");
        menuFile.add(fileSave);
        fileSave.addActionListener(todo -> saveFile());

        JMenu menuEdit = new JMenu("Редактирование");
        menuBar.add(menuEdit);

        JMenuItem editCopy = new JMenuItem("Копировать");
        menuEdit.add(editCopy);
        editCopy.addActionListener(todo -> textArea.copy());

        JMenuItem editCut = new JMenuItem("Вырезать");
        menuEdit.add(editCut);
        editCut.addActionListener(todo -> textArea.cut());

        JMenuItem editPaste = new JMenuItem("Вставить");
        menuEdit.add(editPaste);
        editPaste.addActionListener(todo -> textArea.paste());

        JMenuItem editSelectAll = new JMenuItem("Выбрать все");
        menuEdit.add(editSelectAll);
        editSelectAll.addActionListener(todo -> textArea.selectAll());

    }

    public void newFile() {
        file = null;
    }

    private void loadFile() {
        JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
        dialog.setMultiSelectionEnabled(false);
        try {
            if (dialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                file = dialog.getSelectedFile();
                textArea.setText(readFile(file));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile(File file) throws IOException {
        StringBuilder result = new StringBuilder();
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line + "\n");
        }

        return result.toString();
    }

    private void saveFile() {
        if (file == null) {
            JFileChooser dialog = new JFileChooser(System.getProperty("user.home"));
            dialog.setDialogTitle("сохранить новый файл");
            dialog.showSaveDialog(this);
            file = dialog.getSelectedFile();
        }

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.write(textArea.getText());
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
