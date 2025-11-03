package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static lotto.domain.LottoNumber.*;

public class LottoMachine {

    public List<Lotto> purchaseLotto(int amount) {
        validateAmount(amount);
        int count = calculateLottoCount(amount);
        return generateLottos(count);
    }

    private void validateAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %d원 단위로 입력해야 합니다.", LOTTO_PRICE)
            );
        }
    }

    private int calculateLottoCount(int amount) {
        return amount / LOTTO_PRICE;
    }

    private List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_NUMBER, MAX_NUMBER, LOTTO_NUMBER_COUNT
        );

        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return new Lotto(sortedNumbers);
    }
}