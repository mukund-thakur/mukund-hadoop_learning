/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */

@SuppressWarnings("all")
/** A basic schema for storing Twitter messages */
@org.apache.avro.specific.AvroGenerated
public class Tweet extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Tweet\",\"namespace\":\"com.mukund.avro\",\"doc\":\"A basic schema for storing Twitter messages\",\"fields\":[{\"name\":\"username\",\"type\":\"string\",\"doc\":\"Name of the user account on Twitter.com\"},{\"name\":\"tweet\",\"type\":\"string\",\"doc\":\"The content of the user's Twitter message\"},{\"name\":\"timestamp\",\"type\":\"long\",\"doc\":\"Unix epoch time in seconds\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  /** Name of the user account on Twitter.com */
  @Deprecated public java.lang.CharSequence username;
  /** The content of the user's Twitter message */
  @Deprecated public java.lang.CharSequence tweet;
  /** Unix epoch time in seconds */
  @Deprecated public long timestamp;

  /**
   * Default constructor.
   */
  public Tweet() {}

  /**
   * All-args constructor.
   */
  public Tweet(java.lang.CharSequence username, java.lang.CharSequence tweet, java.lang.Long timestamp) {
    this.username = username;
    this.tweet = tweet;
    this.timestamp = timestamp;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return username;
    case 1: return tweet;
    case 2: return timestamp;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: username = (java.lang.CharSequence)value$; break;
    case 1: tweet = (java.lang.CharSequence)value$; break;
    case 2: timestamp = (java.lang.Long)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'username' field.
   * Name of the user account on Twitter.com   */
  public java.lang.CharSequence getUsername() {
    return username;
  }

  /**
   * Sets the value of the 'username' field.
   * Name of the user account on Twitter.com   * @param value the value to set.
   */
  public void setUsername(java.lang.CharSequence value) {
    this.username = value;
  }

  /**
   * Gets the value of the 'tweet' field.
   * The content of the user's Twitter message   */
  public java.lang.CharSequence getTweet() {
    return tweet;
  }

  /**
   * Sets the value of the 'tweet' field.
   * The content of the user's Twitter message   * @param value the value to set.
   */
  public void setTweet(java.lang.CharSequence value) {
    this.tweet = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * Unix epoch time in seconds   */
  public java.lang.Long getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the value of the 'timestamp' field.
   * Unix epoch time in seconds   * @param value the value to set.
   */
  public void setTimestamp(java.lang.Long value) {
    this.timestamp = value;
  }

  /** Creates a new Tweet RecordBuilder */
  public static Tweet.Builder newBuilder() {
    return new Tweet.Builder();
  }
  
  /** Creates a new Tweet RecordBuilder by copying an existing Builder */
  public static Tweet.Builder newBuilder(Tweet.Builder other) {
    return new Tweet.Builder(other);
  }
  
  /** Creates a new Tweet RecordBuilder by copying an existing Tweet instance */
  public static Tweet.Builder newBuilder(Tweet other) {
    return new Tweet.Builder(other);
  }
  
  /**
   * RecordBuilder for Tweet instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Tweet>
    implements org.apache.avro.data.RecordBuilder<Tweet> {

    private java.lang.CharSequence username;
    private java.lang.CharSequence tweet;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(Tweet.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(Tweet.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Tweet instance */
    private Builder(Tweet other) {
            super(Tweet.SCHEMA$);
      if (isValidValue(fields()[0], other.username)) {
        this.username = data().deepCopy(fields()[0].schema(), other.username);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.tweet)) {
        this.tweet = data().deepCopy(fields()[1].schema(), other.tweet);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[2].schema(), other.timestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /** Gets the value of the 'username' field */
    public java.lang.CharSequence getUsername() {
      return username;
    }
    
    /** Sets the value of the 'username' field */
    public Tweet.Builder setUsername(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.username = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'username' field has been set */
    public boolean hasUsername() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'username' field */
    public Tweet.Builder clearUsername() {
      username = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'tweet' field */
    public java.lang.CharSequence getTweet() {
      return tweet;
    }
    
    /** Sets the value of the 'tweet' field */
    public Tweet.Builder setTweet(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.tweet = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'tweet' field has been set */
    public boolean hasTweet() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'tweet' field */
    public Tweet.Builder clearTweet() {
      tweet = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'timestamp' field */
    public java.lang.Long getTimestamp() {
      return timestamp;
    }
    
    /** Sets the value of the 'timestamp' field */
    public Tweet.Builder setTimestamp(long value) {
      validate(fields()[2], value);
      this.timestamp = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'timestamp' field has been set */
    public boolean hasTimestamp() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'timestamp' field */
    public Tweet.Builder clearTimestamp() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public Tweet build() {
      try {
        Tweet record = new Tweet();
        record.username = fieldSetFlags()[0] ? this.username : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.tweet = fieldSetFlags()[1] ? this.tweet : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.timestamp = fieldSetFlags()[2] ? this.timestamp : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
