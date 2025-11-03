package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_MESSAGE = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "\n보너스 번호를 입력해 주세요.";
    private static final String DELIMITER = ",";

    public int readPurchaseAmount() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
        return parseNumber(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println(WINNING_NUMBERS_MESSAGE);
        String input = Console.readLine();
        return parseNumbers(input);
    }

    public int readBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE);
        return parseNumber(Console.readLine());
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.");
        }
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해야 합니다.");
        }
    }
}