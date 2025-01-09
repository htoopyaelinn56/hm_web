package org.may.hmweb

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommonViewModel : ViewModel() {
    // StateFlow for the selected item
    private val _selectedItem = MutableStateFlow<ItemData?>(null)
    val selectedItem: StateFlow<ItemData?> get() = _selectedItem.asStateFlow()

    // Function to update the selected item
    fun setSelectedItem(itemData: ItemData) {
        _selectedItem.value = itemData
    }
}