package lotto.domain;

import static lotto.domain.LottoNumber.*;

public class WinningLotto {

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {
        validateBonusNumber(bonusNumber);
        validateBonusDuplicate(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < MIN_NUMBER || bonusNumber > MAX_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.", MIN_NUMBER, MAX_NUMBER)
            );
        }
    }

    private void validateBonusDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.countMatch(winningNumbers.getNumbers());
        boolean bonusMatch = lotto.contains(bonusNumber);
        return Rank.valueOf(matchCount, bonusMatch);
    }

}