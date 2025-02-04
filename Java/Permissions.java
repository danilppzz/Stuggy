import java.util.*;

public class PermissionsDecoder {
    private static final Map<String, Integer> Permissions = Map.of(
        "POST", 1,
        "COMMENT", 2,
        "DELETE", 4,
        "UPDATE", 8
    );

    public static Map<String, String> decodePermissions(Integer permissionValue) {
        if (permissionValue == null || permissionValue == 0) {
            return Map.of("permissions", "DEFAULT");
        }

        boolean isValid = Permissions.values().stream().anyMatch(value -> (permissionValue & value) != 0);
        if (!isValid) {
            return Map.of("permissions", "INVALID PERMISSION VALUE");
        }

        List<String> decodedPermissions = new ArrayList<>();
        for (String permission : Permissions.keySet()) {
            if ((permissionValue & Permissions.get(permission)) != 0) {
                decodedPermissions.add(permission.toUpperCase());
            }
        }

        return Map.of("permissions", String.join(", ", decodedPermissions));
    }
}
