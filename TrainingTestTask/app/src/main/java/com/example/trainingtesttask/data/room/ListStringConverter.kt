package com.example.trainingtesttask.data.room

import androidx.room.TypeConverter
import com.example.trainingtesttask.data.models.DTOmodels.Address

class ListStringConverter {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString("|") // Используем | как разделитель
    }

    @TypeConverter
    fun toList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.split("|")
    }
}

class AddressConverter {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return "${address.town}|${address.street}|${address.house}"
    }

    @TypeConverter
    fun toAddress(data: String): Address {
        val parts = data.split("|")
        return Address(parts[0], parts[1], parts[2])
    }
}