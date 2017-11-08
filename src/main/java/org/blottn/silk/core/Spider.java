package org.blottn.silk.core;

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
        Nest nest = new Nest(prey.getActivity());
        nest.insert(prey);
//        Cursor cursor = nest.getAll();
//        while (cursor.moveToNext()) {
//            System.out.println("cursor here:");
//            for (String column : cursor.getColumnNames()) {
//                System.out.println("Iterated: " + column);
//                System.out.println(column + ": " + cursor.getString(cursor.getColumnIndex(column)));
//            }
//        }
//        cursor.close();

    }
}

