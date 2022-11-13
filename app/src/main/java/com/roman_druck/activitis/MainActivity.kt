package com.roman_druck.activitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.roman_druck.data.MainDb
import com.roman_druck.entitis.Item
import com.roman_druck.roomtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = MainDb.getDb(this)
        db.getDao().getAllItem().asLiveData().observe(this){ list->
            binding.tvText.text = ""
            list.forEach {
                val text = " ${it.id}  ${it.name}  ${it.price}\n"
                binding.tvText.append(text)
            }
        }

        binding.btSave.setOnClickListener{
            val item = Item(null,
                binding.adName.text.toString(),
                binding.adPrice.text.toString()

            )
            Thread{
                db.getDao().insertItem(item)
            }.start()
        }


    }
}