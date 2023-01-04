package com.example.catinder.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Gallery
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.catinder.Adapter.KolodaSampleAdapter
import com.example.catinder.DataBase.DbManager
import com.example.catinder.R
import com.yalantis.library.Koloda
import com.yalantis.library.KolodaListener
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var koloda: Koloda
    private lateinit var dislike: ImageView
    private lateinit var like: ImageView
    private var adapter: KolodaSampleAdapter? = null
    private val data = ArrayList<String>()
    var cardsSwiped = 0

    private val dbManager = DbManager(this) //Инициализация бд-менеджера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Обложка в ресурсах ${R.drawable.card}")
        initElement()
        initializeDeck()
        getCats()
        setUpCLickListeners()
    }

    private fun initElement() {
        koloda = findViewById(R.id.koloda)
        dislike = findViewById(R.id.dislike)
        like = findViewById(R.id.like)
    }

    private fun initializeDeck() {
        koloda.kolodaListener = object : KolodaListener {

            override fun onNewTopCard(position: Int) {
                cardsSwiped++
            //    Toast.makeText(this@MainActivity, "${cardsSwiped}", Toast.LENGTH_SHORT).show()
                if(cardsSwiped == 11){
                    getNewCats()
                    cardsSwiped = 0
                }
            }

            override fun onCardSwipedLeft(position: Int) {
                //Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
            }

            override fun onCardSwipedRight(position: Int) {
               // Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
                dbManager.openDb() //Открытие бд
                dbManager.insertToDb(adapter!!.getItem(cardsSwiped-2)) //Запись
                dbManager.closeDb() //Закрытие бд
            }

            override fun onEmptyDeck() {
                //todo realize your logic
            }
        }
    }

    private fun setUpCLickListeners() {
        dislike.setOnClickListener {
            koloda.onClickLeft()
           // Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
        }
        like.setOnClickListener {
            koloda.onClickRight()
           // Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
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
        R.id.actionGallery -> {
            val i = Intent(this, GalleryActivity::class.java)
            startActivity(i)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun getCats(){

        data.clear()
        val URL = "https://api.thecatapi.com/v1/images/search?limit=10" //Формируем url с запросом для api
        val queue = Volley.newRequestQueue(this) //Инициализация переменной для передачи запроса
        val stringRequest = StringRequest(Request.Method.GET, URL, { //Передача запроса и получение ответа
                response -> //Случай удачного результата отклика api
            val obj = JSONArray(response) //Получение json файла

            //    val res = obj.getJSONArray("Value") //Работа с заголовком current json
            for (i in 0 until obj.length()) {
                data.add(obj.getJSONObject(i).getString("url"))
            }
            data.add(R.drawable.card.toString())
            setAdapter()
        }, {
                error -> //Случай неудачного результата отклика api
            Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
            println(error.toString())

        })
        queue.add(stringRequest) //Добавление запроса в очередь

    }

    fun getNewCats(){

        data.clear()
        val URL = "https://api.thecatapi.com/v1/images/search?limit=10"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, URL, {
                response ->
            val obj = JSONArray(response)
            for (i in 0 until obj.length()) {
                println(obj.getJSONObject(i).getString("url"))
                data.add(obj.getJSONObject(i).getString("url"))
            }
            data.add(R.drawable.card.toString())
            adapter?.setData(data)
            koloda.adapter = adapter
            koloda.reloadAdapterData()
        }, {
                error -> //Случай неудачного результата отклика api
            Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
            println(error.toString())

        })
        queue.add(stringRequest) //Добавление запроса в очередь

    }

    private fun setAdapter(){
        adapter = KolodaSampleAdapter(this, data)
        koloda.adapter = adapter
        koloda.isNeedCircleLoading = false
    }

}