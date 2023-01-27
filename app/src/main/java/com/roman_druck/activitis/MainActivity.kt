package com.roman_druck.activitis

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.roman_druck.data.Dao
import com.roman_druck.data.MainDb
import com.roman_druck.entitis.Item
import com.roman_druck.roomtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.itemDb(this)
        val itemDao = db.itemDao()

        binding.btSubmit.setOnClickListener {
            val name = binding.adName.text.toString()
            searchItemByName(itemDao, name)
        }

        binding.btSave.setOnClickListener {
            val item = Item(
                null,
                binding.adName.text.toString(),
                binding.adPrice.text.toString()
            )
            Thread {
                itemDao.insertItem(item)
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
            }.start()
            Thread.sleep(1000) // Add a delay to wait for the thread to finish
        }

    }

    private fun searchItemByName(itemDao: Dao, name: String) {
        itemDao.getItemByName(name).observe(this) { item ->
            if (item != null) {
                val text = "     ${item.name}   \n"
                //val text2 = item.price
                //binding.adPrice.append(text2)
                binding.tvText.append(text)

            } else {
                Toast.makeText(this, "No item found with name $name", Toast.LENGTH_SHORT).show()
            }
        }

    }
}