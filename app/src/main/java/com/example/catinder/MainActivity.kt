package com.example.catinder

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.yalantis.library.Koloda
import com.yalantis.library.KolodaListener

class MainActivity : AppCompatActivity() {
    private lateinit var koloda: Koloda
    private lateinit var dislike: ImageView
    private lateinit var like: ImageView
    private var adapter: KolodaSampleAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initElement()
        initializeDeck()
        fillData()
        setUpCLickListeners()
    }

    private fun initElement() {
        koloda = findViewById(R.id.koloda)
        dislike = findViewById(R.id.dislike)
        like = findViewById(R.id.like)
    }

    private fun initializeDeck() {
        koloda.kolodaListener = object : KolodaListener {

            internal var cardsSwiped = 0

            override fun onNewTopCard(position: Int) {
                //todo realize your logic
            }

            override fun onCardSwipedLeft(position: Int) {
                Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
            }

            override fun onCardSwipedRight(position: Int) {
                Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
            }

            override fun onEmptyDeck() {
                //todo realize your logic
            }
        }
    }

    private fun fillData() {
        val data = ArrayList<String>()
        data.add("https://kupit-kota.ru/wp-content/uploads/2022/07/kak-uhajivat-za-kotenkom-2048x1536.jpeg")
        data.add("https://pic.rutubelist.ru/video/17/b1/17b100a0bcbc6e5e8d11101cde21aca7.jpg")
        data.add("https://avatars.mds.yandex.net/i?id=501eb8a6f4c7a712a7cac75606ce17be-4230996-images-thumbs&ref=rim&n=33&w=150&h=150")
        data.add("https://i.pinimg.com/736x/bc/07/7d/bc077d3bee3ed22557493f47378a3f83.jpg")
        adapter = KolodaSampleAdapter(this, data)
        koloda.adapter = adapter
        koloda.isNeedCircleLoading = true
    }

    private fun setUpCLickListeners() {
        dislike.setOnClickListener {
            koloda.onClickLeft()
            Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
        }
        like.setOnClickListener {
            koloda.onClickRight()
            Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { //Подключаем меню к action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_reload, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) { //Слушаем нажатие на кнопку по id
        R.id.actionReload -> {
            koloda.reloadAdapterData()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}