package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

import java.util.List;

public class OutputView {

    private static final String PURCHASE_COUNT_FORMAT = "\n%d개를 구매했습니다.\n";
    private static final String LOTTO_NUMBERS_FORMAT = "%s\n";
    private static final String STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String RANK_FORMAT = "%s - %d개";
    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %.1f%%입니다.\n";

    public void printPurchaseResult(List<Lotto> lottos) {
        System.out.printf(PURCHASE_COUNT_FORMAT, lottos.size());
        lottos.forEach(this::printLotto);
    }

    public void printStatistics(LottoResult result) {
        System.out.println(STATISTICS_HEADER);
        printRank(Rank.FIFTH, result);
        printRank(Rank.FOURTH, result);
        printRank(Rank.THIRD, result);
        printRank(Rank.SECOND, result);
        printRank(Rank.FIRST, result);
        printProfitRate(result);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    private void printLotto(Lotto lotto) {
        System.out.printf(LOTTO_NUMBERS_FORMAT, lotto.getNumbers());
    }

    private void printRank(Rank rank, LottoResult result) {
        String rankInfo = rank.getDescription();
        int count = result.getRankCount(rank);
        System.out.println(String.format(RANK_FORMAT, rankInfo, count));
    }

    private void printProfitRate(LottoResult result) {
        double profitRate = result.calculateProfitRate();
        System.out.printf(PROFIT_RATE_FORMAT, profitRate);
    }

}