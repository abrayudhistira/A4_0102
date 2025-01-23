package abrayudhistira.cobafinal.ui.property

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.repository.PropertiRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PropertiViewModel : ViewModel() {

    private val repository = PropertiRepository()

    fun getProperti(): LiveData<List<Properti>> {
        return repository.getProperti()
    }
}