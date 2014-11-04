package unit;

/**

 */
public class FunctionDefineZodiacOfGivenDate {
    //Даны два целых числа: D (день) и M (месяц), определяющие правильную дату.
    // Вывести знак Зодиака, соответствующий этой дате: «Водолей» (20.1–18.2),
    // «Рыбы» (19.2–20.3), «Овен» (21.3–19.4), «Телец» (20.4–20.5), «Близнецы» (21.5–21.6),
    // «Рак» (22.6–22.7), «Лев» (23.7–22.8), «Дева» (23.8–22.9), «Весы» (23.9–22.10),
    // «Скорпион» (23.10–22.11), «Стрелец» (23.11–21.12), «Козерог» (22.12–19.1).
    //http://coolcode.ru/reshaem-zadachi-abramyan-na-paskale-case20/

    public static void main(String[] args) {
        int D = 12;
        int M = 2;

        System.out.println(defineZodiac(D, M));
    }

    static String defineZodiac(int D, int M) {
        int x = M * 100 + D;

        if (120 <= x && x <= 218) {
            return "Водолей";
        } else if (219 <= x && x <= 320) {
            return "Рыбы";
        } else if (1222 <= x && x <= 1231 || 101 <= x && x <= 119) {
            return "Козерог";
        }
        return "Cant detect, data is incorrect, probably";
    }
}