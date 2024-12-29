package service;

import data.Repositories;
import domain.Flower;

import java.util.List;

public class FlowerService {
    public List<Flower> getFlowers() {
        return Repositories.getFlowerRepository().getFlowers();
    }
}
