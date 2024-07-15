package controller;

import domain.*;
import view.InputVIew;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class CarController {

    private static final int INITIAL_NUMBER = 0;

    private final InputVIew inputVIew;
    private final OutputView outputView;
    private final CreateRandomNumber createRandomNumber;

    public CarController(InputVIew inputVIew, OutputView outputView, CreateRandomNumber createRandomNumber) {
        this.inputVIew = inputVIew;
        this.outputView = outputView;
        this.createRandomNumber = createRandomNumber;
    }

    public void run() {
        Cars cars = new Cars(getCarNames());
        List<Car> carBundle = cars.getCars();
        int moveCarChance = getChance();
        outputView.runGuide();
        moveCars(carBundle, moveCarChance);
        makeCarWinner(cars);
    }

    private List<String> getCarNames() {
        outputView.inputCarNamesGuide();
        CarNameParser carNameParser = new CarNameParser(inputVIew.inputCarNames());
        return carNameParser.getCarNames();
    }

    private int getChance() {
        outputView.getChanceGuide();
        return inputVIew.inputChance();
    }

    private void moveCar(List<Car> carBundle) {
        for (Car car : carBundle) {
            car.moveCar(createRandomNumber);
            outputView.showCar(car.getCarName(), car.getMoveCount());
        }
        outputView.separateLine();
    }

    private void moveCars(List<Car> carBundle, int moveCarChance) {
        for (int i = INITIAL_NUMBER; i < moveCarChance; i++) {
            moveCar(carBundle);
        }
    }

    private void makeCarWinner(Cars cars) {
        List<String> carNames = new ArrayList<>();
        List<Integer> moveCounts = new ArrayList<>();
        for (Car car : cars.getCars()) {
            carNames.add(car.getCarName());
            moveCounts.add(car.getMoveCount());
        }
        CarWinner carWinner = new CarWinner(carNames, moveCounts);
        outputView.showWinner(carWinner.getCarWinner());
    }
}
