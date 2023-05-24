package Dao;

import Model.Time;

public interface TimeDaoInterface {
    int adicionarTime(Time time);
    int buscarIdTimePorNome(Time time);
}
