package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {

    private final Map<Rank, Integer> rankCounts;
    private final int totalAmount;

    public LottoResult(List<Lotto> lottos, WinningLotto winningLotto, int totalAmount) {
        this.rankCounts = calculateRankCounts(lottos, winningLotto);
        this.totalAmount = totalAmount;
    }

    private Map<Rank, Integer> calculateRankCounts(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Long> countLottoResult = lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(
                        rank -> rank,
                        rank -> countLottoResult.getOrDefault(rank, 0L).intValue()
                ));
    }

    public int getRankCount(Rank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public double calculateProfitRate() {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / totalAmount * 100;
    }

    private long calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

}