package com.example.demo.templates;

import java.util.*;

public class RowModel {

    private Integer rowId;

    private List<Integer> seats;

    public RowModel(Integer rowId, List<Integer> seats) {
        this.rowId = rowId;
        this.seats = seats;
    }

    public static List<RowModel> createRowsWithSeats(List<SeatRequest> seats){
        Map<Integer, List<Integer>> rowsMap = new HashMap<>();
        List<RowModel> rowModels = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            if (Objects.isNull(rowsMap.get(seats.get(i).getRowNumber()))){
                rowsMap.put(seats.get(i).getRowNumber(),new ArrayList<>());
                rowModels.add(new RowModel(seats.get(i).getRowNumber()));
                rowsMap.get(seats.get(i).getRowNumber()).add(seats.get(i).getSeatNumber());
            }else{
                rowsMap.get(seats.get(i).getRowNumber()).add(seats.get(i).getSeatNumber());
            }
        }

        for (int i = 0; i < rowModels.size(); i++) {
            rowModels.get(i).seats = rowsMap.get(rowModels.get(i).rowId);
        }

        return  rowModels;
    }

    public RowModel(Integer rowId) {
        this.rowId = rowId;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void addSeat(Integer seat) {
        this.seats.add(seat);
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }

    public Integer getRowId() {
        return rowId;
    }
}
