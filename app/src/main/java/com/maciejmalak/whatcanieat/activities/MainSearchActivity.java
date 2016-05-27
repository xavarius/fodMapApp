package com.maciejmalak.whatcanieat.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.maciejmalak.whatcanieat.R;
import com.maciejmalak.whatcanieat.foodList.FoodAdapter;
import com.maciejmalak.whatcanieat.foodList.FoodPojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.Bind;
import butterknife.ButterKnife;

/*TODO: Ikona lupki ładna*/
/*TODO: Żeby było widać co user powiedział w searchView i może w dialogu recognition (search confy?) */
/*TODO: strings in EN i PL */
/* TODO: Kolory w Drawer*/
/*TODO: Stworzyć, wypełnić, obsłużyć DB*/
/*TODO: Filtrowanie wyników w czasie rzeczywistym: pokazujemy wszystkie zawierające daną frazę? !!!*/
/*TODO: Wymyśleć co z 2x aktywność: coś o diecie fodmap oraz about krótkie */
/* TODO: Jeśli dodasz ProGuard dodaj wyjątek dla ButterKnife */
/*TODO: Ikona appki*/
/*TODO: Może jakieś śmieszne ikony do Drawera np zamiast lupki marchewka przy Search food etc. */

public class MainSearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) protected Toolbar mToolbar;
    @Bind(R.id.drawer_layout) protected DrawerLayout mDrawer;
    @Bind(R.id.nav_view) protected NavigationView mNavigationView;

    private SearchView searchView;

    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            /*It's supported in onNewIntent */
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.d("maciek 2  " , newText);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initDefaultViews();
        initListView();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.search_toolbar);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(onQueryTextListener);

        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final ComponentName name = getComponentName();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(name));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        final int id = item.getItemId();

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntents(intent);
    }

    private void handleIntents(final Intent intent) {
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("maciek   ", "heheh:    " + query);
        }
    }

    private void initDefaultViews() {

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initListView() {
        final ArrayList<FoodPojo> list = new ArrayList<>();
        list.add(new FoodPojo("Cebula","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Czosnek","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Karczoch","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Fasola","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Czarna fasola","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Burak","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Bób","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Maniok","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Kalafior","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Seler naciowy","Warzywa","Restricted","max 5 cm łodygi"));
        list.add(new FoodPojo("Por","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Magetout","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Grzyby","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Groch","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Czerwona fasola","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Cebula dymka","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Szalotka","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Taro","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Jabłka","Owoce","Forbidden",""));
        list.add(new FoodPojo("Morele","Owoce","Forbidden",""));
        list.add(new FoodPojo("Awokado","Owoce","Forbidden",""));
        list.add(new FoodPojo("Jeżyny","Owoce","Forbidden",""));
        list.add(new FoodPojo("Wiśnie","Owoce","Forbidden",""));
        list.add(new FoodPojo("Porzeczki","Owoce","Forbidden",""));
        list.add(new FoodPojo("Daktyle","Owoce","Forbidden",""));
        list.add(new FoodPojo("Figi","Owoce","Forbidden",""));
        list.add(new FoodPojo("Jagody goji","Owoce","Forbidden",""));
        list.add(new FoodPojo("Grapefruit","Owoce","Forbidden",""));
        list.add(new FoodPojo("Liczi","Owoce","Forbidden",""));
        list.add(new FoodPojo("Mango","Owoce","Forbidden",""));
        list.add(new FoodPojo("Nektarynki","Owoce","Forbidden",""));
        list.add(new FoodPojo("Brzoskwinie","Owoce","Forbidden",""));
        list.add(new FoodPojo("Gruszki","Owoce","Forbidden",""));
        list.add(new FoodPojo("Kiwano","Owoce","Forbidden",""));
        list.add(new FoodPojo("Ananas suszony","Owoce","Forbidden",""));
        list.add(new FoodPojo("Śliwki","Owoce","Forbidden",""));
        list.add(new FoodPojo("Śliwki suszone","Owoce","Forbidden",""));
        list.add(new FoodPojo("Rodzynki","Owoce","Forbidden",""));
        list.add(new FoodPojo("Tamarillo","Owoce","Forbidden",""));
        list.add(new FoodPojo("Arbuz","Owoce","Forbidden",""));
        list.add(new FoodPojo("Chorizo","Mięso","Forbidden",""));
        list.add(new FoodPojo("Kiełbasa","Mięso","Forbidden",""));
        list.add(new FoodPojo("Wędliny","Mięso","Forbidden",""));
        list.add(new FoodPojo("Chleb pszenny","Zbożowe","Restricted","max 1 kromka"));
        list.add(new FoodPojo("Herbatniki","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Bułka tarta","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Ciasta","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Rogal","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Makaron jajeczny","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Makaron pszeniczny","Zbożowe","Restrcted","max 1/2 filiżanki"));
        list.add(new FoodPojo("Makaron Udon","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Otręby pszenne","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Płatki pszenne","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Mąka migdałowa","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Mąka pszenna","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Kiełki pszenicy","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Mąka amarantusowa","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Jęczmień","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Otręby ","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Chleb razowy pełnoziarnisty","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Chleb wieloziarnisty","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Chlebek Naan","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Chleb owsiany","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Pumpernikiel","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Granola","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Płatki zbożowe","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Muesli","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Gnocchi","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Pistacje","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Żyto","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Kasza manna","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Mąka orkiszowa","Zbożowe","Forbidden",""));
        list.add(new FoodPojo("Agawa","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Hummus","Inne","Forbidden",""));
        list.add(new FoodPojo("Fruktoza","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Syrop kukurydziany","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Miód","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Pesto","Inne","Forbidden",""));
        list.add(new FoodPojo("Dżem pigwowy","Inne","Forbidden",""));
        list.add(new FoodPojo("Warzywa konserwowe","Warzywa","Forbidden",""));
        list.add(new FoodPojo("Kostka rosołowa","Inne","Forbidden",""));
        list.add(new FoodPojo("Poliole","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Izomalt","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Inulina","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Izomalt","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Maltitol","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Mannitol","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Sorbitol","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Ksylitol","Słodziki","Forbidden",""));
        list.add(new FoodPojo("Tahini","Inne","Forbidden",""));
        list.add(new FoodPojo("Tzatziki","Inne","Forbidden",""));
        list.add(new FoodPojo("Piwo","Napoje","Restricted","max 1 butelka"));
        list.add(new FoodPojo("Woda kokosowa","Napoje","Forbidden",""));
        list.add(new FoodPojo("Malewka jabłkowa z 50-100% naturalnego soku","Napoje","Forbidden",""));
        list.add(new FoodPojo("Nalewka malinowa z 50-100% naturalnego soku","Napoje","Forbidden",""));
        list.add(new FoodPojo("Herbata owocowa z jabłkiem","Napoje","Forbidden",""));
        list.add(new FoodPojo("Sok jabłkowy","Napoje","Restricted","Niewielkie ilości"));
        list.add(new FoodPojo("Sok gruszkowy","Napoje","Restricted","Niewielkie ilości"));
        list.add(new FoodPojo("Sok mango","Napoje","Restricted","Niewielkie ilości"));
        list.add(new FoodPojo("Sok pomarańczowy","Napoje","Restricted","max 100 ml"));
        list.add(new FoodPojo("Rum","Napoje","Forbidden",""));
        list.add(new FoodPojo("Napoje gazowane","Napoje","Forbidden",""));
        list.add(new FoodPojo("Mleko sojowe","Napoje","Forbidden",""));
        list.add(new FoodPojo("Chai tea ","Napoje","Forbidden",""));
        list.add(new FoodPojo("Rumianek","Napoje","Restricted","niezbyt mocna"));
        list.add(new FoodPojo("Napar z kopru","Napoje","Forbidden",""));
        list.add(new FoodPojo("Herbata ziołowa","Napoje","Restricted","niezbyt mocna"));
        list.add(new FoodPojo("Herbata oolong","Napoje","Forbidden",""));
        list.add(new FoodPojo("Wino ","Napoje","Restricted","max 1 kieliszek"));
        list.add(new FoodPojo("Maślanka","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Sery kremowe","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Halloumi","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Ricotta","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Śmietanka","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Budyń","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Lody","Inne","Forbidden",""));
        list.add(new FoodPojo("Kefir","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko krowie","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko kozie","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko zagęszczone","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko ryżowe","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Mleko owcze","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Kwaśna śmietana","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Jogurt","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Jogurt grecki","Nabiał","Forbidden",""));
        list.add(new FoodPojo("Karob","Inne","Forbidden",""));
        list.add(new FoodPojo("Lucerna ","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pędy bambusa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Kiełki fasoli","Warzywa","Allowed",""));
        list.add(new FoodPojo("Brokuły","Warzywa","Restricted","max 1/2 szklanki"));
        list.add(new FoodPojo("Bok choy","Warzywa","Allowed",""));
        list.add(new FoodPojo("Brukselka","Warzywa","Restricted","max 2 główki"));
        list.add(new FoodPojo("Pak choi","Warzywa","Allowed",""));
        list.add(new FoodPojo("Dynia piżmowa","Warzywa","Restricted","max 1/4 szklanki"));
        list.add(new FoodPojo("Kapusta biała","Warzywa","Restricted","max 1 filiżanka"));
        list.add(new FoodPojo("Kapusta czerwona","Warzywa","Restricted","max 1 filiżanka"));
        list.add(new FoodPojo("Marchewka","Warzywa","Allowed",""));
        list.add(new FoodPojo("Seler (korzeń)","Warzywa","Allowed",""));
        list.add(new FoodPojo("Cykoria","Warzywa","Allowed",""));
        list.add(new FoodPojo("Cieciorka","Warzywa","Restricted","max 1/4 szklanki"));
        list.add(new FoodPojo("Chilli","Warzywa","Allowed",""));
        list.add(new FoodPojo("Szczypiorek","Warzywa","Allowed",""));
        list.add(new FoodPojo("Kukurydza","Warzywa","Restricted","max 1/2 kolby, małe ilości"));
        list.add(new FoodPojo("Cukinia","Warzywa","Allowed",""));
        list.add(new FoodPojo("Ogórek","Warzywa","Allowed",""));
        list.add(new FoodPojo("Bakłażan","Warzywa","Allowed",""));
        list.add(new FoodPojo("Koper włoski","Warzywa","Allowed",""));
        list.add(new FoodPojo("Fasolka szparagowa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Papryka","Warzywa","Allowed",""));
        list.add(new FoodPojo("Imbir","Warzywa","Allowed",""));
        list.add(new FoodPojo("Jarmuż","Warzywa","Allowed",""));
        list.add(new FoodPojo("Soczewica","Warzywa","Restricted","małe ilości"));
        list.add(new FoodPojo("Sałata masłowa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Sałata lodowa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Sałata radicchio","Warzywa","Allowed",""));
        list.add(new FoodPojo("Rukola","Warzywa","Allowed",""));
        list.add(new FoodPojo("Kabaczek","Warzywa","Allowed",""));
        list.add(new FoodPojo("Okra","Warzywa","Allowed",""));
        list.add(new FoodPojo("Oliwki","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pasternak","Warzywa","Allowed",""));
        list.add(new FoodPojo("Groszek cukrowy ","Warzywa","Restricted","max 5 strączków"));
        list.add(new FoodPojo("Ziemniaki","Warzywa","Allowed",""));
        list.add(new FoodPojo("Dynia piżmowa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Dynia konserwowa","Warzywa","Restricted","max 1/4 szklanki"));
        list.add(new FoodPojo("Rzodkiewka","Warzywa","Allowed",""));
        list.add(new FoodPojo("Wodorosty nori","Warzywa","Allowed",""));
        list.add(new FoodPojo("Botwinka","Warzywa","Allowed",""));
        list.add(new FoodPojo("Dynia makaronowa","Warzywa","Allowed",""));
        list.add(new FoodPojo("Szpinak baby","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pomidory suszone","Warzywa","Restricted","max 4 sztuki"));
        list.add(new FoodPojo("Pomidory suszone","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pomidorki cherry","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pomidory koktajlowe","Warzywa","Allowed",""));
        list.add(new FoodPojo("Pomidory rzymskie","Warzywa","Allowed",""));
        list.add(new FoodPojo("Bataty","Warzywa","Restricted","1/2 szklanki"));
        list.add(new FoodPojo("Kasztany ","Warzywa","Allowed",""));
        list.add(new FoodPojo("Ignam","Warzywa","Allowed",""));
        list.add(new FoodPojo("Bligia ","Owoce","Allowed",""));
        list.add(new FoodPojo("Banany","Owoce","Allowed",""));
        list.add(new FoodPojo("Jagody ","Owoce","Allowed",""));
        list.add(new FoodPojo("Karambole","Owoce","Allowed",""));
        list.add(new FoodPojo("Żurawina","Owoce","Allowed",""));
        list.add(new FoodPojo("Klementynki","Owoce","Allowed",""));
        list.add(new FoodPojo("Smoczy owoc","Owoce","Allowed",""));
        list.add(new FoodPojo("Winogrona","Owoce","Allowed",""));
        list.add(new FoodPojo("Dragonfruit","Owoce","Allowed",""));
        list.add(new FoodPojo("Melon","Owoce","Allowed",""));
        list.add(new FoodPojo("Kiwi","Owoce","Allowed",""));
        list.add(new FoodPojo("Cytryny","Owoce","Allowed",""));
        list.add(new FoodPojo("Limonki","Owoce","Allowed",""));
        list.add(new FoodPojo("Mandarynki","Owoce","Allowed",""));
        list.add(new FoodPojo("Pomarańcze","Owoce","Allowed",""));
        list.add(new FoodPojo("Marakuja","Owoce","Allowed",""));
        list.add(new FoodPojo("Papaja","Owoce","Allowed",""));
        list.add(new FoodPojo("Ananas ","Owoce","Allowed",""));
        list.add(new FoodPojo("Maliny","Owoce","Allowed",""));
        list.add(new FoodPojo("Rabarbar","Owoce","Allowed",""));
        list.add(new FoodPojo("Truskawki","Owoce","Allowed",""));
        list.add(new FoodPojo("Tamaryndowiec","Owoce","Allowed",""));
        list.add(new FoodPojo("Tangelo","Owoce","Allowed",""));
        list.add(new FoodPojo("Wołowina","Mięso","Allowed",""));
        list.add(new FoodPojo("Tangerynka","Owoce","Allowed",""));
        list.add(new FoodPojo("Kurczak","Mięso","Allowed",""));
        list.add(new FoodPojo("Baranina","Mięso","Allowed",""));
        list.add(new FoodPojo("Wieprzowina","Mięso","Allowed",""));
        list.add(new FoodPojo("Prosciutto","Mięso","Allowed",""));
        list.add(new FoodPojo("Quorn","Mięso","Allowed",""));
        list.add(new FoodPojo("Indyk","Mięso","Allowed",""));
        list.add(new FoodPojo("Tatar","Mięso","Allowed",""));
        list.add(new FoodPojo("Tuńczyk w puszce","Ryby","Allowed",""));
        list.add(new FoodPojo("Dorsz","Ryby","Allowed",""));
        list.add(new FoodPojo("Plamiak","Ryby","Allowed",""));
        list.add(new FoodPojo("Gładzica","Ryby","Allowed",""));
        list.add(new FoodPojo("Łosoś","Ryby","Allowed",""));
        list.add(new FoodPojo("Pstrąg","Ryby","Allowed",""));
        list.add(new FoodPojo("Tuńczyk","Ryby","Allowed",""));
        list.add(new FoodPojo("Krab","Ryby","Allowed",""));
        list.add(new FoodPojo("Homar","Ryby","Allowed",""));
        list.add(new FoodPojo("Małże","Ryby","Allowed",""));
        list.add(new FoodPojo("Ostrygi","Ryby","Allowed",""));
        list.add(new FoodPojo("Krewetki","Ryby","Allowed",""));
        list.add(new FoodPojo("Pieczywo bezglutenowe","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chleb kukurydziany","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chleb owsiany","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chleb ryżowy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chleb orkiszowy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Mąka ziemniaczana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Makaron bezglutenowy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Migdały","Ziarna","Restricted","max 15"));
        list.add(new FoodPojo("Orzechy brazylijskie","Ziarna","Allowed",""));
        list.add(new FoodPojo("Otręby owsiane","Ziarna","Allowed",""));
        list.add(new FoodPojo("Otręby ryżowe","Ziarna","Allowed",""));
        list.add(new FoodPojo("Kasza bulgur","Ziarna","Restricted","1/4 szklanki (ugotowanej)"));
        list.add(new FoodPojo("Kasza gryczana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Mąka gryczana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Makaron gryczany","Ziarna","Allowed",""));
        list.add(new FoodPojo("Ryż brązowy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Ryż pełnoziarnisty","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chipsy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Frytki","Ziarna","Allowed",""));
        list.add(new FoodPojo("Mąka kukurydziana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Pieczywo chrupkie","Ziarna","Allowed",""));
        list.add(new FoodPojo("Płatki kukurydziane","Ziarna","Restricted","1/2 szklanki"));
        list.add(new FoodPojo("Mleko kokosowe","Ziarna","Allowed",""));
        list.add(new FoodPojo("Kokos","Owoce","Allowed",""));
        list.add(new FoodPojo("Tortille kukurydziane","Ziarna","Restricted","max 3"));
        list.add(new FoodPojo("Orzechy laskowe","Ziarna","Restricted","max 15"));
        list.add(new FoodPojo("Orzechy makadamia","Ziarna","Allowed",""));
        list.add(new FoodPojo("Proso","Ziarna","Allowed",""));
        list.add(new FoodPojo("Kasza jaglana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Mąka owsiana","Ziarna","Restricted","max 1/2 szklanki"));
        list.add(new FoodPojo("Owies","Ziarna","Allowed",""));
        list.add(new FoodPojo("Ciasteczka owsiane","Ziarna","Allowed",""));
        list.add(new FoodPojo("Orzeszki ziemne","Ziarna","Allowed",""));
        list.add(new FoodPojo("Orzechy pekan","Ziarna","Restricted","max 15"));
        list.add(new FoodPojo("Orzeszki pinii","Ziarna","Restricted","max 15"));
        list.add(new FoodPojo("Polenta","Ziarna","Allowed",""));
        list.add(new FoodPojo("Popcorn","Ziarna","Allowed",""));
        list.add(new FoodPojo("Owsianka","Ziarna","Allowed",""));
        list.add(new FoodPojo("Płatki owsiane","Ziarna","Allowed",""));
        list.add(new FoodPojo("Quinoa","Ziarna","Allowed",""));
        list.add(new FoodPojo("Komosa ryżowa","Ziarna","Allowed",""));
        list.add(new FoodPojo("Ryż basmanti","Ziarna","Allowed",""));
        list.add(new FoodPojo("Makaron ryżowy","Ziarna","Allowed",""));
        list.add(new FoodPojo("Płatki ryżowe","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chia","Inne","Allowed",""));
        list.add(new FoodPojo("Pestki dyni","Inne","Allowed",""));
        list.add(new FoodPojo("Mak","Inne","Allowed",""));
        list.add(new FoodPojo("Nasiona słonecznika","Inne","Allowed",""));
        list.add(new FoodPojo("Sezam","Inne","Allowed",""));
        list.add(new FoodPojo("Skrobia ziemniaczana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Skrobia kukurydziana","Ziarna","Allowed",""));
        list.add(new FoodPojo("Sorgo","Ziarna","Allowed",""));
        list.add(new FoodPojo("Chipsy tortilla","Inne","Allowed",""));
        list.add(new FoodPojo("Orzechy włoskie","Inne","Allowed",""));
        list.add(new FoodPojo("Aspartam","Słodziki","Allowed",""));
        list.add(new FoodPojo("Acesulfam K","Słodziki","Allowed",""));
        list.add(new FoodPojo("Sos barbecue","Inne","Allowed",""));
        list.add(new FoodPojo("Kapary","Inne","Allowed",""));
        list.add(new FoodPojo("Czekolada gorzka","Słodziki","Allowed",""));
        list.add(new FoodPojo("Czekolada mleczna","Słodziki","Restricted","max 3 kostki"));
        list.add(new FoodPojo("Czekolada biała","Słodziki","Restricted","max 3 kostki"));
        list.add(new FoodPojo("Chutney","Inne","Restricted","max 1 łyżka "));
        list.add(new FoodPojo("Sos rybny","Inne","Allowed",""));
        list.add(new FoodPojo("Golden syrup","Słodziki","Allowed",""));
        list.add(new FoodPojo("Glukoza","Słodziki","Allowed",""));
        list.add(new FoodPojo("Dżem truskawkowy","Inne","Allowed",""));
        list.add(new FoodPojo("Ketchup","Inne","Restricted","niewielka ilość"));
        list.add(new FoodPojo("Syrop klonowy","Słodziki","Allowed",""));
        list.add(new FoodPojo("Marmolada","Inne","Allowed",""));
        list.add(new FoodPojo("Marmite","Inne","Allowed",""));
        list.add(new FoodPojo("Majonez","Inne","Allowed",""));
        list.add(new FoodPojo("Pasta miso","Inne","Allowed",""));
        list.add(new FoodPojo("Musztarda","Inne","Allowed",""));
        list.add(new FoodPojo("Sos z ostryg","Inne","Allowed",""));
        list.add(new FoodPojo("Masło orzechowe","Inne","Allowed",""));
        list.add(new FoodPojo("Sacharyna","Słodziki","Allowed",""));
        list.add(new FoodPojo("Pasta z krewetek","Inne","Allowed",""));
        list.add(new FoodPojo("Sos sojowy","Inne","Allowed",""));
        list.add(new FoodPojo("Stewia","Słodziki","Allowed",""));
        list.add(new FoodPojo("Sukraloza","Słodziki","Allowed",""));
        list.add(new FoodPojo("Sacharoza","Słodziki","Allowed",""));
        list.add(new FoodPojo("Ocet jabłkowy","Inne","Restricted","max 2 łyżki"));
        list.add(new FoodPojo("Ocet balsamiczny","Inne","Restricted","max 2 łyżki"));
        list.add(new FoodPojo("Ocet ryżowy","Inne","Allowed",""));
        list.add(new FoodPojo("Wasabi","Inne","Allowed",""));
        list.add(new FoodPojo("Sos Worcestershire","Inne","Allowed",""));
        list.add(new FoodPojo("Wódka","Napoje","Allowed",""));
        list.add(new FoodPojo("Gin","Napoje","Allowed",""));
        list.add(new FoodPojo("Whisky","Napoje","Allowed",""));
        list.add(new FoodPojo("Espresso","Napoje","Allowed",""));
        list.add(new FoodPojo("Lemoniada","Napoje","Allowed","w małych ilościach"));
        list.add(new FoodPojo("Odżywka białkowa","Napoje","Allowed",""));
        list.add(new FoodPojo("Mleko sojowe z białka sojowego","Napoje","Allowed",""));
        list.add(new FoodPojo("Zielona herbata","Napoje","Allowed",""));
        list.add(new FoodPojo("Herbata miętowa","Napoje","Allowed",""));
        list.add(new FoodPojo("Biała herbata","Napoje","Allowed",""));
        list.add(new FoodPojo("Woda","Napoje","Allowed",""));
        list.add(new FoodPojo("Ser brie","Nabiał","Allowed",""));
        list.add(new FoodPojo("Ser camembert","Nabiał","Allowed",""));
        list.add(new FoodPojo("Ser cheddar","Nabiał","Allowed",""));
        list.add(new FoodPojo("Serek wiejski","Nabiał","Allowed",""));
        list.add(new FoodPojo("Ser feta","Nabiał","Allowed",""));
        list.add(new FoodPojo("Ser kozi","Nabiał","Allowed",""));
        list.add(new FoodPojo("Mozzarella","Nabiał","Allowed",""));
        list.add(new FoodPojo("Parmezan","Nabiał","Allowed",""));
        list.add(new FoodPojo("Jajka","Nabiał","Allowed",""));
        list.add(new FoodPojo("Margaryna","Nabiał","Allowed",""));
        list.add(new FoodPojo("Masło ","Nabiał","Allowed",""));
        list.add(new FoodPojo("Mleko migdałowe","Nabiał","Allowed",""));
        list.add(new FoodPojo("Mleko konopne","Nabiał","Allowed",""));
        list.add(new FoodPojo("Mleko bez laktozy","Nabiał","Allowed",""));
        list.add(new FoodPojo("Mleko owsiane","Nabiał","Restricted","max 30 ml"));
        list.add(new FoodPojo("Sorbet","Nabiał","Allowed",""));
        list.add(new FoodPojo("Białko sojowe","Nabiał","Allowed",""));
        list.add(new FoodPojo("Ser szwajcarski","Nabiał","Allowed",""));
        list.add(new FoodPojo("Tempeh","Nabiał","Allowed",""));
        list.add(new FoodPojo("Tofu","Nabiał","Allowed",""));
        list.add(new FoodPojo("Bita śmietana","Nabiał","Allowed",""));
        list.add(new FoodPojo("Bazylia","Inne","Allowed",""));
        list.add(new FoodPojo("Kolendra","Inne","Allowed",""));
        list.add(new FoodPojo("Curry","Inne","Allowed",""));
        list.add(new FoodPojo("Kozieradka","Inne","Allowed",""));
        list.add(new FoodPojo("Trawa cytrynowa","Inne","Allowed",""));
        list.add(new FoodPojo("Mięta","Inne","Allowed",""));
        list.add(new FoodPojo("Oregano","Inne","Allowed",""));
        list.add(new FoodPojo("Pietruszka","Inne","Allowed",""));
        list.add(new FoodPojo("Rozmaryn","Inne","Allowed",""));
        list.add(new FoodPojo("Estragon","Inne","Allowed",""));
        list.add(new FoodPojo("Tymianek","Inne","Allowed",""));
        list.add(new FoodPojo("Czarny pieprz","Inne","Allowed",""));
        list.add(new FoodPojo("Kardamon","Inne","Allowed",""));
        list.add(new FoodPojo("Cynamon","Inne","Allowed",""));
        list.add(new FoodPojo("Goździki","Inne","Allowed",""));
        list.add(new FoodPojo("Kminek","Inne","Allowed",""));
        list.add(new FoodPojo("Koper włoski","Inne","Allowed",""));
        list.add(new FoodPojo("Anyż","Inne","Allowed",""));
        list.add(new FoodPojo("Gałka muszkatołowa","Inne","Allowed",""));
        list.add(new FoodPojo("Gorczyca","Inne","Allowed",""));
        list.add(new FoodPojo("Papryka słodka","Inne","Allowed",""));
        list.add(new FoodPojo("Szafran","Inne","Allowed",""));
        list.add(new FoodPojo("Kurkuma","Inne","Allowed",""));
        list.add(new FoodPojo("Olej z awokado","Inne","Allowed",""));
        list.add(new FoodPojo("Olej rzepakowy","Inne","Allowed",""));
        list.add(new FoodPojo("Olej kokosowy","Inne","Allowed",""));
        list.add(new FoodPojo("Olej z oliwek","Inne","Allowed",""));
        list.add(new FoodPojo("Olej z orzeszków ziemnych","Inne","Allowed",""));
        list.add(new FoodPojo("Olej sezamowy","Inne","Allowed",""));
        list.add(new FoodPojo("Olej słonecznikowy","Inne","Allowed",""));
        list.add(new FoodPojo("Proszek do pieczenia","Inne","Allowed",""));
        list.add(new FoodPojo("Kakao","Inne","Allowed",""));
        list.add(new FoodPojo("Żelatyna","Inne","Allowed",""));
        list.add(new FoodPojo("Masło klarowane","Inne","Allowed",""));
        list.add(new FoodPojo("Ghee","Inne","Allowed",""));
        list.add(new FoodPojo("Smalec","Inne","Allowed",""));
        list.add(new FoodPojo("Sól","Inne","Allowed",""));

        Collections.sort(list, new Comparator<FoodPojo>() {
                    public int compare(FoodPojo f1, FoodPojo f2) {
                        return f1.getName().compareTo(f2.getName());
                    }
        });

        final FoodAdapter foodAdapter = new FoodAdapter(this, list);
        final ListView listView = (ListView) findViewById(R.id.food_list);
        listView.setAdapter(foodAdapter);
    }
}
