package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("6개 일치하면 1등이다.")
    @Test
    void 당첨번호가_6개_일치하면_1등() {
        Rank rank = Rank.valueOf(6, false);
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다.")
    @Test
    void 당첨번호가_5개_일치_보너스_일치하면_2등() {
        Rank rank = Rank.valueOf(5, true);
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("5개 일치하고 보너스 번호가 불일치하면 3등이다.")
    @Test
    void 당첨번호가_5개_일치_보너스_불일치하면_3등() {
        Rank rank = Rank.valueOf(5, false);
        assertThat(rank).isEqualTo(Rank.THIRD);
    }

    @DisplayName("4개 일치하면 4등이다.")
    @Test
    void 당첨번호가_4개_일치하면_4등() {
        Rank rank = Rank.valueOf(4, false);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("3개 일치하면 5등이다.")
    @Test
    void 당첨번호가_3개_일치하면_5등() {
        Rank rank = Rank.valueOf(3, false);
        assertThat(rank).isEqualTo(Rank.FIFTH);
    }

    @DisplayName("3개 미만 일치하면 낙첨이다.")
    @Test
    void 당첨번호가_3개_미만_일치하면_낙첨() {
        Rank rank = Rank.valueOf(2, false);
        assertThat(rank).isEqualTo(Rank.NONE);
    }

    @DisplayName("4개 일치하고 보너스 번호가 일치해도 4등이다.")
    @Test
    void 당첨번호가_4개_일치_보너스_일치해도_4등() {
        Rank rank = Rank.valueOf(4, true);
        assertThat(rank).isEqualTo(Rank.FOURTH);
    }

    @DisplayName("1등 상금은 2,000,000,000원이다.")
    @Test
    void 당첨번호가_1등_상금() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
    }

    @DisplayName("2등 상금은 30,000,000원이다.")
    @Test
    void 당첨번호가_2등_상금() {
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
    }

    @DisplayName("낙첨 상금은 0원이다.")
    @Test
    void 낙첨_상금() {
        assertThat(Rank.NONE.getPrize()).isEqualTo(0);
    }

}