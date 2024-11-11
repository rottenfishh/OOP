package ru.nsu.kolodina.strings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoyerMooreTest {
    BoyerMoore boyerMoore;
    @BeforeEach
    void setUp() {
        boyerMoore = new BoyerMoore();
        try {
            File myObj = new File("file.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Test
    public void testSmall() {
        try {
            FileWriter myWriter = new FileWriter("file.txt");
            myWriter.write("abrakadabra");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String pattern = "bra";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file.txt", pattern);
        List<Integer> excepted = new ArrayList<>();
        excepted.add(1);
        excepted.add(8);
        assertEquals(excepted, resultBoyerMoore);
    }
    @Test
    public void testBig() {
        Random rand = new Random();
        List<String> inputs = new ArrayList<>();
        inputs.add("dad");
        inputs.add("daddy");
        inputs.add("mordadd");
        inputs.add("daapres");
        inputs.add("peepoopeepoo");
        inputs.add("hahahasistro");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            int n = rand.nextInt(6);
            sb.append(inputs.get(n));
        }
        try {
            FileWriter myWriter = new FileWriter("file.txt");
            myWriter.write(sb.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String pattern = "pepo";
        List<Integer> resultBoyerMoore = boyerMoore.findInFile("file.txt", pattern);
        Integer[] arr = {0, 6, 15, 21, 69, 75, 84, 90, 96, 102, 125, 131, 186, 192, 198, 204, 210, 216, 222, 228, 270, 276, 358, 364, 457, 463,
                512, 518, 529, 535, 596, 602, 608, 614, 620, 626, 651, 657, 729, 735, 827, 833, 846, 852, 898, 904, 978, 984, 990, 996, 1041, 1047, 1053, 1059, 1077, 1083, 1149,
                1155, 1166, 1172, 1181, 1187, 1250, 1256, 1281, 1287, 1315, 1321, 1368, 1374, 1398, 1404, 1410, 1416, 1470, 1476, 1527, 1533, 1553, 1559, 1565, 1571, 1608, 1614,
                1654, 1660, 1673, 1679, 1713, 1719, 1764, 1770, 1838, 1844, 1872, 1878, 1884, 1890, 1903, 1909, 2071, 2077, 2167, 2173, 2292, 2298, 2437, 2443, 2456, 2462, 2489,
                2495, 2551, 2557, 2643, 2649, 2655, 2661, 2822, 2828, 2848, 2854, 2934, 2940, 2956, 2962, 2990, 2996, 3043, 3049, 3055, 3061, 3162, 3168, 3174, 3180, 3206, 3212,
                3242, 3248, 3276, 3282, 3312, 3318, 3336, 3342, 3392, 3398, 3435, 3441, 3452, 3458, 3511, 3517, 3564, 3570, 3576, 3582, 3612, 3618, 3627, 3633, 3639, 3645, 3651,
                3657, 3668, 3674, 3680, 3686, 3726, 3732, 3738, 3744, 3750, 3756, 3762, 3768, 3774, 3780, 3819, 3825, 3855, 3861, 3886, 3892, 3937, 3943, 3961, 3967, 4003, 4009,
                4015, 4021, 4044, 4050, 4056, 4062, 4087, 4093, 4128, 4134, 4162, 4168, 4186, 4192, 4210, 4216, 4265, 4271, 4277, 4283, 4311, 4317, 4347, 4353, 4376, 4382, 4442,
                4448, 4457, 4463, 4528, 4534, 4574, 4580, 4610, 4616, 4622, 4628, 4707, 4713, 4736, 4742, 4751, 4757, 4804, 4810, 4816, 4822, 4833, 4839, 4898, 4904, 4925, 4931,
                5019, 5025, 5160, 5166, 5196, 5202, 5215, 5221, 5227, 5233, 5282, 5288, 5306, 5312, 5404, 5410, 5440, 5446, 5495, 5501, 5528, 5534, 5683, 5689, 5698, 5704, 5730,
                5736, 5848, 5854, 5893, 5899, 5953, 5959, 5989, 5995, 6050, 6056, 6181, 6187, 6235, 6241, 6266, 6272, 6278, 6284, 6364, 6370, 6393, 6399, 6426, 6432, 6455, 6461,
                6502, 6508, 6534, 6540, 6556, 6562, 6611, 6617, 6775, 6781, 6802, 6808, 6814, 6820, 6980, 6986, 7060, 7066, 7085, 7091, 7097, 7103, 7109, 7115, 7143, 7149, 7223,
                7229, 7240, 7246, 7259, 7265, 7271, 7277, 7351, 7357, 7370, 7376, 7401, 7407, 7445, 7451, 7512, 7518, 7544, 7550, 7556, 7562, 7580, 7586, 7609, 7615 };
        List<Integer> excepted = Arrays.asList(arr);
        assertEquals(excepted, resultBoyerMoore);
    }
}
