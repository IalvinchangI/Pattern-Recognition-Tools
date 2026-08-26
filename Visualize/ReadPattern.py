import json
import os


class PatternReader():
    """
    read pattern
    
    @author IalvinchangI
    """

    def __init__(self, path: str):
        """ read pattern from the pattern file """
        if (os.path.exists(path) == False):
            raise AttributeError("This file does not exist.")
        
        _, extension = os.path.splitext(path)
        if extension != ".iai":
            raise AttributeError(f"The extension of this file ({path}) is not 'iai'")

        self.__file = open(path)
        pattern: dict = json.load(self.__file)

        if ("fileType" not in pattern) or (pattern["fileType"] != "IaI.PatternRecognition.raw"):
            raise AttributeError("This file is not pattern file.")
        
        self.pattern     = pattern["pattern"]
        self.velocity    = pattern["velocity"]
        self.strokeWidth = pattern["strokeWidth"]
        self.label       = pattern["label"]


    def close(self) -> None:
        self.__file.close()


    def __enter__(self):
        return self
    def __exit__(self, exc_type, exc_value, traceback):
        if not exc_type and not exc_value and not traceback:
            return True
        else:
            print(exc_type, exc_value, traceback, sep="\n")
    

    @staticmethod
    def read_directory(directory_path: str):
        """ read all the patterns in the directory """

        for file_path in os.listdir(directory_path):
            path = os.path.join(directory_path, file_path)
            if os.path.isfile(path) != True:
                continue

            try:
                with PatternReader(path) as pattern:
                    yield pattern
            except AttributeError as e:
                print(e)
                continue
        
        return None

if __name__ == "__main__":
    patterns = PatternReader.read_directory("Visualize\\patterns")
    for pattern in patterns:
        if (pattern == None):
            break
        print(pattern.pattern)
        print(pattern.velocity)
        print(pattern.strokeWidth)
        print(pattern.label)