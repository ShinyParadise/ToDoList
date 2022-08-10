package com.example.todolist.db;

public final class MigrationContracts {
    private MigrationContracts() {}

    public static final String MIGRATE_TO_VERSION_2 = String.format(
            "ALTER TABLE %s ADD COLUMN %s INTEGER DEFAULT NULL",
            DatabaseContract.ListItemTable.TABLE_NAME,
            DatabaseContract.ListItemTable.COLUMN_LAST_UPDATE
    );
}
