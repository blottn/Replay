package blottn.org.silk;

import android.database.Cursor;

/**
 * Created by Nick on 18/10/2017.
 *
 * Functions effectively as a logger/ web listener
 *
 */

public class Spider {

    public Spider() {

    }

    public void feed(Prey prey) {
        System.out.println("Feeding time");
        Nest nest = new Nest(prey.getActivity());
        nest.insert(prey);
        System.out.println("Pushed into db");
        Cursor cursor = nest.query(0,"wowow");  //TODO remove
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(Nest.WebDatabaseEntry.COLUMN_NAME_TYPE));
            System.out.println("Iterated: " + name);
        }
        cursor.close();

    }
}

