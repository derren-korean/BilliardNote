package com.auto.billiardnote

import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Environment
import android.text.InputType
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.auto.billiardnote.databinding.ActivityMainBinding
import com.auto.billiardnote.fao.NoteRepository
import com.auto.billiardnote.ui.home.HomeFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var mAppBarConfiguration: AppBarConfiguration? = null
    private var binding: ActivityMainBinding? = null
    val repo:NoteRepository by lazy {
        NoteRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setSupportActionBar(binding!!.appBarMain.toolbar)

//        val homeFragment: HomeFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as HomeFragment

        binding!!.appBarMain.fab.setOnClickListener { v: View? ->
            // TODO: load input data if exist
            val noteName = EditText(this)
            noteName.inputType = InputType.TYPE_CLASS_TEXT
            val builder = AlertDialog.Builder(
                binding!!.root.context
            )
            builder.setView(noteName)
                .setTitle("노트 저장하기")
                .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
//                    homeFragment.save(noteName.text.toString())
                    // TODO: 저장할 때 타이틀 입력하기 & path serialize에 특수문자 때문에 JSON malformed occurred!
                    Snackbar.make((v)!!, "저장완료!!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                })
                .setNegativeButton("취소"
                ) { dialog, _ -> dialog.cancel() }
            val dialog = builder.create()
            dialog.show()
        }
        val drawer = binding!!.drawerLayout
        val navigationView = binding!!.navView
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,
            R.id.nav_gallery,
            R.id.nav_slideshow
        )
            .setOpenableLayout(drawer)
            .build()
        val navController = findNavController(this, R.id.nav_host_fragment_content_main)
        setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        setupWithNavController(navigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(this, R.id.nav_host_fragment_content_main)
        return (navigateUp(navController, mAppBarConfiguration!!)
                || super.onSupportNavigateUp())
    }

//    companion object {
//        private val isExternalStorageReadOnly: Boolean
//            private get() {
//                val extStorageState = Environment.getExternalStorageState()
//                return Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState
//            }
//        private val isExternalStorageAvailable: Boolean
//            private get() {
//                val extStorageState = Environment.getExternalStorageState()
//                return Environment.MEDIA_MOUNTED == extStorageState
//            }
//    }
}