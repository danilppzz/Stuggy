const Permissions = {
    POST: 1,
    COMMENT: 2,
    DELETE: 4,
    UPDATE: 8,
} as const;

type PermissionKey = keyof typeof Permissions;

interface DecodedPermissions {
    permissions: string;
}

function decodePermissions(permissionValue: number | null | undefined): DecodedPermissions {
    if (permissionValue == null || permissionValue === 0) {
        return { permissions: "DEFAULT" };
    }

    const isValid = Object.values(Permissions).some(value => (permissionValue & value) !== 0);
    if (!isValid) {
        return { permissions: "INVALID PERMISSION VALUE" };
    }

    const decodedPermissions = Object.keys(Permissions)
        .filter((permission) => (permissionValue & Permissions[permission as PermissionKey]) !== 0)
        .map((permission) => permission.toUpperCase());

    return { permissions: decodedPermissions.join(", ") };
}

export { decodePermissions, Permissions };
