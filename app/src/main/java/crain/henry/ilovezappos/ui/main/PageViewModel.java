package crain.henry.ilovezappos.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> index = new MutableLiveData<>();

    public void setIndex(int index) {
        this.index.setValue(index);
    }
}