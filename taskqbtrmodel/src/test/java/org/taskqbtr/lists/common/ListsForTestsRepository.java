package org.taskqbtr.lists.common;

import org.junit.Ignore;
import org.taskqbtr.lists.model.List;

@Ignore
public class ListsForTestsRepository {

    public static List inbox() {
        return new List("inbox");
    }
    public static List waitingFor() {
        return new List("waiting for");
    }
    public static List nextActions() {
        return new List("next actions");
    }
    public static List reference() {
        return new List("reference");
    }
}
