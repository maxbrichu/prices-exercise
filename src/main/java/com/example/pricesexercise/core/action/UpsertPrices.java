package com.example.pricesexercise.core.action;

import com.example.pricesexercise.core.domain.Price;
import com.example.pricesexercise.core.infrastructure.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pricesexercise.core.infrastructure.utils.FileUtils.loadFromCSV;

@Service
public class UpsertPrices {
    private final PricesRepository prices;

    @Autowired
    public UpsertPrices(PricesRepository prices) {
        this.prices = prices;
    }

    //Observable<Price>
    public void execute(String filePath) {
        List<Price> filePrices = loadFromCSV(filePath, Price::new);

//        return Observable.just(filePrices)
//                .subscribeOn(Schedulers.io())
//                .flatMapIterable(list -> list)
//                .map(price -> {
        filePrices.parallelStream().forEach(price -> {
                    try {
                        prices.upsert(price);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        System.out.println("ERROR when upsert price: " + price.toJsonObject() +
                                " Message: " + exception.getMessage());
                    }

                });
    }
}