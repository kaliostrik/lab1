package lab1.texteditor;

import sun.jvm.hotspot.ui.Editor;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main implements SwingConstants {
    public static void main(final String[] args) throws IOException, URISyntaxException {
        final Window window = new Window();
        window.setVisible(true);
    }
}
