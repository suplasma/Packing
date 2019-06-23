package ru.suplasma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Window extends JFrame {
    private static final int startX = 40, startY = 100, size = 15;
    private Color[] colorBlocks;
    private LinkedList<int[][][]> list;
    private int containerNumber;
    private int depth;

    Window(int width, int height, int length, int numberBlocks) {
        super("Packing");
        setBounds(0, 0, 800, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Random random = new Random(Integer.MAX_VALUE);
        colorBlocks = new Color[numberBlocks + 1];
        colorBlocks[0] = Color.WHITE;
        for (int i = 1; i <= numberBlocks; i++)
            colorBlocks[i] = new Color(random.nextInt());
        list = new LinkedList<>();
        containerNumber = 0;
        depth = 0;
        read(width, height, length);
        repaint();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    containerNumber++;
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    containerNumber--;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    depth++;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    depth--;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (list.isEmpty())
            return;
        Graphics2D d = (Graphics2D) g;
        if (containerNumber < 0)
            containerNumber = 0;
        if (containerNumber >= list.size())
            containerNumber = list.size() - 1;
        if (depth < 0)
            depth = 0;
        if (depth >= list.get(0)[0].length)
            depth = list.get(0)[0].length - 1;

        for (int i = 0; i < list.get(containerNumber).length; i++)
            for (int t = 0; t < list.get(containerNumber)[0][0].length; t++) {
                d.setColor(colorBlocks[list.get(containerNumber)[i][depth][t]]);
                d.fillRect(startX + size * t + 1, startY + size * i + 1, size - 2, size - 2);
            }

        d.setColor(Color.BLACK);
        d.setFont(new Font("Times new roman", Font.BOLD, 20));
        d.drawString((containerNumber + 1) + " Контейнер", 40, 60);
        d.drawString((depth + 1) + " Часть контейнера", 40, 80);
    }

    private void read(int width, int height, int length) {
        try {
            File file = new File("result.txt");
            if (!file.exists()) {
                System.out.println("Ошибка, файла не существует");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
                return;
            }

            FileReader fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);

            while (scanner.hasNextLine()) {
                scanner.nextLine();
                int[][][] container = new int[width][height][length];
                for (int i = 0; i < container.length; i++)
                    for (int j = 0; j < container[0].length; j++)
                        for (int t = 0; t < container[0][0].length; t++)
                            container[i][j][t] = scanner.nextInt();

                scanner.nextLine();
                scanner.nextLine();
                scanner.nextLine();
                list.add(container);
            }

            scanner.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Ошибка чтения файла result.txt");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.exit(0);
        }
    }
}
