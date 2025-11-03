package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @DisplayName("보너스 번호가 1보다 작으면 예외가 발생한다.")
    @Test
    void 보너스_번호가_1보다_작으면_예외() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 45보다 크면 예외가 발생한다.")
    @Test
    void 보너스_번호가_45보다_크면_예외() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 46))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("6개 일치하면 1등을 반환한다.")
    @Test
    void 당첨번호가_6개_일치하면_1등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto testLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Rank rank = winningLotto.match(testLotto);

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등을 반환한다.")
    @Test
    void 당첨번호가_5개_일치하고_보너스_일치하면_2등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto testLotto = new Lotto(List.of(1, 2, 3, 4, 5, 7));

        Rank rank = winningLotto.match(testLotto);

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호가 불일치하면 3등을 반환한다.")
    @Test
    void 당첨번호가_5개_일치하고_보너스_불일치하면_3등() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto testLotto = new Lotto(List.of(1, 2, 3, 4, 5, 8));

        Rank rank = winningLotto.match(testLotto);

        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("3개 미만 일치하면 낙첨을 반환한다.")
    @Test
    void 당첨번호가_3개_미만_일치하면_낙첨() {
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(winningNumbers, 7);
        Lotto testLotto = new Lotto(List.of(8, 9, 10, 11, 12, 13));

        Rank rank = winningLotto.match(testLotto);

        assertThat(rank).isEqualTo(Rank.NONE);
    }
}