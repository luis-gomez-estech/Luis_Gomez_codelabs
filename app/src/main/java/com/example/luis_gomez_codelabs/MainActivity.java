package com.example.luis_gomez_codelabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


// Proporciona UI para la pantalla principal.

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Añadimos un Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configuración de ViewPager para cada pestaña
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Para que aparezcan los iconos LIST, TILT Y CARD en la toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        // Crear caja que alberga menu dentro e inflar diseño.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        // Esto hhe puesto nuevo
      //navigationView.setNavigationItemSelectedListener(this);

        // Para poder hacer click en los botones dle menu que esta dentro del icono de la izquiera, he tenido queponer esto
       // navigationView.setNavigationItemSelectedListener(this);



        // Agregar icono de menú (hamburguesa) a la izquierda en la barra de herramientas
        // tambien podemos cambiarle el color en l linea  indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.button_naranja,getTheme()));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator
                    = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            indicator.setTint(ResourcesCompat.getColor(getResources(),R.color.button_naranja,getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
/*
        // Establecer el toggle del cajon de navegación
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Set item in checked state
                        menuItem.setChecked(true);

                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
*/
        // Agregar botón de acción flotante en la parte inferior derecha de la vista principal (boton azul de abajo a la derecha)
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello Snackbar!",
                        Snackbar.LENGTH_LONG).show();
            }
        });



    }

    // Añadir fragmentos a las pestañas
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ListContentFragment(), "List");
        adapter.addFragment(new TileContentFragment(), "Tile");
        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; esto agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
        //return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Aqui controlamos lo que queremos que hagan los botones que estan dentro del icono del menu de la derecha

        int id = item.getItemId();

        Intent i;



        // ninguna inspección simplificable si declaración
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }



        // Aqui con un swich le digo que:

        switch (item.getItemId()){

            // Opcion 1 añadida:
            // Si se hace click en el boton submenu About con id=about_toolbar que esta en el toolbar dentro del icono llamado Mis datos,
            // entonces va a ir a la activity About. Su codigo esta en menu_main.xml),

            case R.id.about_toolbar:
                i = new Intent(MainActivity.this, About.class);
                startActivity(i);
                finish();
                break;


            // Si se hace click en el boton submenu About con id=about_main que esta dentro del icono de la derecha del toolbar (menu main),
            // entonces, tambien va a ir a la activity About

            case R.id.about_main:
                i = new Intent(MainActivity.this, About.class);
                startActivity(i);
                finish();
                break;

            // Opcion 3 añadida:
            // Si se hace click en el boton Setting con id=action_settings en esta dentro
            // del icono menu de la derecha , entonces va a poner un mensaje con Snackbar
            // y dentro de él un boton hacia un Toast y se abrira el Toast con el mensaje

            case R.id.action_settings:
                Snackbar.make(this.findViewById(R.id.ll),
                        "Setting clickeado",
                        Snackbar.LENGTH_SHORT)
                        .setAction("Ver Toast", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this,
                                        "Setting clickeado",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }



}



