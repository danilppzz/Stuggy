class Permissions:
    PERMISSIONS = {
        "POST": 1,
        "COMMENT": 2,
        "DELETE": 4,
        "UPDATE": 8,
    }

    @staticmethod
    def decode_permissions(permission_value):
        if permission_value is None or permission_value == 0:
            return {"permissions": "DEFAULT"}

        is_valid = any(permission_value & value != 0 for value in Permissions.PERMISSIONS.values())
        if not is_valid:
            return {"permissions": "INVALID PERMISSION VALUE"}

        decoded_permissions = [perm.upper() for perm, value in Permissions.PERMISSIONS.items() if permission_value & value != 0]
        
        return {"permissions": ", ".join(decoded_permissions)}
