package com.simpledotatrade.service;

import com.simpledotatrade.analytics.AnalyticsUtils;
import com.simpledotatrade.domain.TradeOffer;
import com.simpledotatrade.misc.TradeSearchFilter;
import com.simpledotatrade.utils.dotahtmlparse.Dota2LoungeParser;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by oles on 13.02.15.
 */
@Service
public class AnalyticsService {

    @com.simpledotatrade.misc.loginject.Logger
    private Logger LOG;

    private static final String D2L_TRADE_PAGE_URL = "http://dota2lounge.com/?p=";

    public List<TradeOffer> getProfitableTradeOffers(TradeSearchFilter tradeSearchFilter) {

        List<TradeOffer> profitableTradeOffers = new LinkedList<>();
        Dota2LoungeParser d2lParser = new Dota2LoungeParser();

        LOG.info("Start parsing D2L trade pages... ");

        int i = 1;
        do {
            String pageURL = D2L_TRADE_PAGE_URL + i;
            List localTradeOffers = d2lParser.parseTradeOfferPage(pageURL, true);
            localTradeOffers = clearUnprofitable(localTradeOffers);
            localTradeOffers = filterTradeOffersByRevenu(localTradeOffers, tradeSearchFilter);
            profitableTradeOffers.addAll(localTradeOffers);

            i++;
        } while (!checkStopCondition(profitableTradeOffers, tradeSearchFilter));

        return profitableTradeOffers;
    }

    private List<TradeOffer> clearUnprofitable(List<TradeOffer> tradeOffers) {

        List<TradeOffer> cleanTradeOffers = new LinkedList<>();

        for (TradeOffer profitableTradeOffer : tradeOffers) {
            TradeOffer tradeOffer = AnalyticsUtils.fillInTradeOfferPrices(profitableTradeOffer);
            if (tradeOffer != null) {
                cleanTradeOffers.add(tradeOffer);
            }
        }

        return cleanTradeOffers;
    }

    private boolean checkStopCondition(List<TradeOffer> profitableTradeOffers, TradeSearchFilter tradeSearchFilter) {

        if (profitableTradeOffers.size() < tradeSearchFilter.getMaxResults()) {
            return false;
        }

        return true; //stop search
    }

    //TODO: REPLACE FILTERS WITH STRATEGY PATTERN!
    private List<TradeOffer> filterTradeOffersByRevenu(List<TradeOffer> tradeOffers, TradeSearchFilter tradeSearchFilter) {

        List<TradeOffer> filteredTradeOffers = new LinkedList<>();
        BigDecimal minProfit = tradeSearchFilter.getMinProfit();

        for (TradeOffer tradeOffer : tradeOffers) {

            int comarisonResult = tradeOffer.getSummDifference().compareTo(minProfit);
            if (comarisonResult >= 0) {
                filteredTradeOffers.add(tradeOffer);
            }
        }

        return filteredTradeOffers;
    }

}
