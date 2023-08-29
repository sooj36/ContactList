package com.example.contact

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.databinding.ActivityMainBinding
import android.view.View
import android.widget.Toast
import com.android.customitemview.MyAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dataList = mutableListOf<MyItem>()
        dataList.add(MyItem(R.drawable.sample_1, "Charlie", "010.0000.0000", true))
        dataList.add(MyItem(R.drawable.sample_2, "Daisy", "010.0000.0001", ))
        dataList.add(MyItem(R.drawable.sample_3, "Duke", "010.0000.0002", true))
        dataList.add(MyItem(R.drawable.sample_4, "Max", "010.0000.0003", true))
        dataList.add(MyItem(R.drawable.sample_5, "Happy", "010.0000.0004", ))
        dataList.add(MyItem(R.drawable.sample_6, "Luna", "010.0000.0005", ))
        dataList.add(MyItem(R.drawable.sample_7, "Bob", "010.0000.0006", ))
        dataList.add(MyItem(R.drawable.sample_8, "Amy", "010.0000.0007", true))
        dataList.add(MyItem(R.drawable.sample_9, "Tom", "010.0000.0008", true))
        dataList.add(MyItem(R.drawable.sample_10, "Carol", "010.0000.0009", true))


        val adapter = MyAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : MyAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val name: String = dataList[position].aName
                Toast.makeText(this@MainActivity," $name 선택!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

