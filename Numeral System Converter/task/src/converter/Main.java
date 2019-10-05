package converter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String abc = "0123456789abcdefghijklmnopqrstuvwxyz";

        int radixSource = 0;
        String numberSource = "";
        int radixTarget = 0;

        String line1 = "";
        String line2 = "";
        String line3 = "";

        if (scanner.hasNextLine()) {
            line1 = scanner.nextLine();
        }
        if (scanner.hasNextLine()) {
            line2 = scanner.nextLine();
        }
        if (scanner.hasNextLine()) {
            line3 = scanner.nextLine();
        }


        boolean u2 = false;
        boolean u1 = line1.matches("[0-9]+");
        if (u1) {
            radixSource = Integer.parseInt(line1);
            if (radixSource <= 9 && radixSource > 0) {
                String regex = "^[\\+\\-]{0,1}[0-" + abc.charAt(radixSource) + "]+";
                String regex2 = "^[\\+\\-]{0,1}[0-" + abc.charAt(radixSource) +"]+[\\.\\,][0-" + abc.charAt(radixSource) + "]+$";
                u2 = line2.matches(regex) || line2.matches(regex2);
            } else if (radixSource <= 35 && radixSource > 0) {
                String regex = "^[\\+\\-]{0,1}[0-9a-" + abc.charAt(radixSource) + "]+";
                String regex2 = "^[\\+\\-]{0,1}[0-" + abc.charAt(radixSource) +"]+[\\.\\,][0-" + abc.charAt(radixSource) + "]+$";
                u2 = line2.matches(regex) || line2.matches(regex2);
            } else {
                u1 = false;
            }
        }
        boolean u3 = line3.matches("[0-9]+");
        if (u3) {
            radixTarget = Integer.parseInt(line3);
            if (!(0 < radixTarget && radixTarget <= 36)) {
                u3 = false;
            }
        }

        if (!(u1 && u2 && u3)) {
            System.out.println("error");
        } else {
            radixSource = Integer.parseInt(line1);
            numberSource = line2;
            radixTarget = Integer.parseInt(line3);
            long number;
            StringBuilder fraction = new StringBuilder("");

            if (radixSource != 1 && !numberSource.contains(".")) {
                number = Long.parseLong(numberSource, radixSource);
            } else if (radixSource != 1) {
                String numberString1 = numberSource.substring(0, numberSource.indexOf('.'));
                String numberString2 = numberSource.substring(numberSource.indexOf('.') + 1);

                number = Long.parseLong(numberString1, radixSource);

                double fractionSource = 0;
                int q = -1;

                for (char c : numberString2.toCharArray()) {
                    fractionSource += (abc.indexOf(c) * Math.pow(radixSource, q));
                    q--;
                }

                int d = 0;
                while (fractionSource != 0 && d < 5) {
                    fraction.append(abc.charAt((int) (fractionSource * radixTarget)));
                    fractionSource = fractionSource * radixTarget - (int) (fractionSource * radixTarget);
                    d++;
                }


            } else {
                number = Long.parseLong(String.valueOf(numberSource.length()));
            }

            if (radixTarget != 1 && !numberSource.contains(".")) {
                System.out.println(Long.toString(number, radixTarget));
            } else if (radixTarget != 1) {
                System.out.print(Long.toString(number, radixTarget));
                System.out.print(".");
                System.out.println(fraction);
            } else {
                for (int i = 0; i < number; i++) {
                    System.out.print(1);
                }
            }
        }
    }
}
