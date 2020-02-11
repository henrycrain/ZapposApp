package crain.henry.ilovezappos;

import java.util.List;

public class OrderBookDict {

    public int timestamp;
    // have to implement the price/amount pairs as Lists because the API implements them as arrays
    // TODO can I make a class?
    public List<List<Float>> bids;
    public List<List<Float>> asks;
}
