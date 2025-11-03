package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController {

    private static final LottoController INSTANCE = new LottoController();

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMachine lottoMachine;

    private LottoController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoMachine = new LottoMachine();
    }

    public static LottoController getInstance() {
        return INSTANCE;
    }

    public void run() {
        int purchaseAmount = readPurchaseAmount();
        List<Lotto> lottos = purchaseLottos(purchaseAmount);
        outputView.printPurchaseResult(lottos);

        WinningLotto winningLotto = readWinningLotto();
        LottoResult result = new LottoResult(lottos, winningLotto, purchaseAmount);
        outputView.printStatistics(result);
    }

    private int readPurchaseAmount() {
        try {
            return inputView.readPurchaseAmount();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readPurchaseAmount();
        }
    }

    private List<Lotto> purchaseLottos(int amount) {
        try {
            return lottoMachine.purchaseLotto(amount);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return purchaseLottos(readPurchaseAmount());
        }
    }

    private WinningLotto readWinningLotto() {
        try {
            List<Integer> winningNumbers = inputView.readWinningNumbers();
            int bonusNumber = inputView.readBonusNumber();
            return new WinningLotto(new Lotto(winningNumbers), bonusNumber);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
            return readWinningLotto();
        }
    }
}